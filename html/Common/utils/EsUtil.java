package html.Common.utils;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.xm.tendency.word.HownetWordTendency;

import html.model.Tiebadate;

public class EsUtil {
	public static void insert(long time,List<String> list){
		HownetWordTendency hownetWordTendency = new HownetWordTendency();
		Date date = new Date();
		TransportClient client = new PreBuiltTransportClient(Settings.builder().put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", false).build())
                .addTransportAddress(new TransportAddress(new InetSocketAddress("39.105.155.193", 9300)));
		for(int i=0;i<list.size();i++){
			Map<String, Object> params = new HashMap<>();
			params.put("title", "fate");
			params.put("date", time);
			params.put("text", list.get(i));
			params.put("dateshow", date);
			params.put("tendency", hownetWordTendency.getTendency(list.get(i)));
			IndexResponse response = client.prepareIndex("tieba-data", "comment", UuidUtils.getUuid()).setSource(params).get();
		}
		client.close();
	}
	
	public static List<String> selectall(){
		TransportClient client = new PreBuiltTransportClient(Settings.builder().put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", false).build())
                .addTransportAddress(new TransportAddress(new InetSocketAddress("39.105.155.193", 9300)));
		List<String> result = new ArrayList<>();
		SearchRequestBuilder builder = client.prepareSearch("tieba-data").setTypes("comment").setSize(10000);
		SearchResponse response = builder.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
		SearchHit[] hits = response.getHits().getHits();
		
		for (SearchHit hit : hits) {
			Map<String, Object> tempMap = hit.getSourceAsMap();
			result.add((String) tempMap.get("text"));
		}
		client.close();
		return result;
	}
	
	public static void insert_generalization(List<Tiebadate> datas){
		int count = 1;
		TransportClient client = new PreBuiltTransportClient(Settings.builder().put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", false).build())
                .addTransportAddress(new TransportAddress(new InetSocketAddress("39.105.155.193", 9300)));
		for(Tiebadate data:datas){
			System.out.println("***********************插入第" + (count++) + "条数据***********************");
			Map<String, Object> sourse = new HashMap<>();
			try {
				sourse = Maptranform.objectToMap(data);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("***********************转换第" + (count-1) + "条数据失败***********************");
				continue;
			}
			IndexResponse response = client.prepareIndex("tieba-data", "comment", UuidUtils.getUuid()).setSource(sourse).get();
		}
		client.close();
		System.out.println("***********************插入完成，共插入" + (count) + "条数据***********************");
	}
	
	public static List<Tiebadate> findbypage(String key, Date time){
		TransportClient client = new PreBuiltTransportClient(Settings.builder().put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", false).build())
                .addTransportAddress(new TransportAddress(new InetSocketAddress("39.105.155.193", 9300)));
		List<String> result = new ArrayList<>();
		List<Tiebadate> datas = new ArrayList<>();
		QueryBuilder queryBuilder = null;
		if(key.equals("")){
			queryBuilder = QueryBuilders.matchAllQuery();
		}else{
			queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("title.keyword", key)).must(QueryBuilders.termQuery("dateshow", time));
		}
		SearchRequestBuilder builder = client.prepareSearch("tieba-data").setTypes("comment").setSize(10000);
		SearchResponse response = builder.setQuery(queryBuilder).execute().actionGet();
		SearchHit[] hits = response.getHits().getHits();
		
		for (SearchHit hit : hits) {
			try {
				Tiebadate data = new Tiebadate(-1, (String)hit.getSourceAsMap().get("title"), (String)hit.getSourceAsMap().get("creater") ,
						(String)hit.getSourceAsMap().get("text"), (String)hit.getSourceAsMap().get("theme"), 
						(double)hit.getSourceAsMap().get("tendency"),DateUtil.getsdfISODate((String)hit.getSourceAsMap().get("dateshow")));
				datas.add(data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
		}
		client.close();
		return datas;
	}
}
