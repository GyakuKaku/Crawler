package html.Common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;

public class ESClient {

//	private static TransportClient client;
//	private static Map<String, String> esMap;
//	private static String index;
//	private static String type;
//	private static Map<String, List<Map<String, Object>>> tdtMap;
//	private static Map<String, String> cmtxpMap;
//	private static final Logger logger = Logger.getLogger(ESClient.class);
//
//	public static synchronized Client getClent() {
//		if (client == null) {
//			esMap = EsUtils.getEsMap();
//			tdtMap = TDTManager.getTDT();
//			cmtxpMap = TDTManager.getCmtxpMap();
//			index = esMap.get("index");
//			type = esMap.get("type");
//			try {
//				init();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//		}
//		return client;
//	}
//
//	/**
//	 * 初始化连接
//	 * @throws UnknownHostException 
//	 * @throws NumberFormatException 
//	 */
//	public static void init() {
//		Settings settings = Settings.settingsBuilder()
//				.put("client.transport.sniff", true)
//				.put("cluster.name", esMap.get("cluster.name")).build();
//		
//		try {
//			
//			client = TransportClient.builder().settings(settings).build()  
//	                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esMap
//							.get("hostname")), Integer.parseInt(esMap.get("port")))); 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//
//	/**
//	 * 关闭连接
//	 */
//	public void close() {
//		client.close();
//	}
//
//	/**
//	 * 创建索引
//	 *
//	 * @param jsondata
//	 * @return
//	 */
//	public IndexResponse createIndexResponse(String jsondata) {
//		IndexResponse response = client.prepareIndex(index, type)
//				.setSource(jsondata).execute().actionGet();
//		return response;
//	}
//
//	/**
//	 * 建立索引
//	 *
//	 * @param jsondata
//	 */
//	public void createIndexResponse(List<String> jsondata) {
//		// 创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
//		IndexRequestBuilder requestBuilder = client.prepareIndex(index, type)
//				.setRefresh(true);
//		for (int i = 0; i < jsondata.size(); i++) {
//			requestBuilder.setSource(jsondata.get(i)).execute().actionGet();
//		}
//	}
//
//	/**
//	 * 删除索引
//	 *
//	 * @param id
//	 */
//	public void deleteIndexResponse(String id) {
////		DeleteResponse response = client.prepareDelete(index, type, id)
////				.setOperationThreaded(false).execute().actionGet();
//	}
//
//	/**
//	 * 根据id获取数据
//	 */
//	public static String getLogById(String index, String type, String id) {
//		GetResponse responseGet = client.prepareGet(index, type, id).execute()
//				.actionGet();
//		return responseGet.getSourceAsString();
//	}
//
//	public void search() {
////		SearchResponse response = client.prepareSearch(index)
////				.setTypes(type)
////				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
////				.setQuery(QueryBuilders.termQuery("name", "fox"))
////				// Query
////				.setPostFilter(
////						FilterBuilders.rangeFilter("age").from(20).to(30)) // Filter
////				.setFrom(0).setSize(60).setExplain(true).execute().actionGet();
////		SearchHits hits = response.getHits();
////		System.out.println(hits.getTotalHits());
////		for (int i = 0; i < hits.getHits().length; i++) {
////			System.out.println(hits.getHits()[i].getSourceAsString());
////		}
//	}
//
//	/**
//	 * 根据页面输入请求查询条件查找日志
//	 *
//	 * @param model
//	 * @return
//	 */
//	public static Map<String, Object> getLogsbyBtsp(SysEsPar bean,SysEsModel model,boolean isPaging) {
//		// 页面首次加载默认查询最近两天日志
//		if (model.getStartTime() == null) {
//			model.setStartTime(DateUtil.getLastDate(-2));
//		}
//
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		Map<String, Object> logMap;
//		SearchRequestBuilder builder;
//		if(isPaging){
//			int size = model.getRows();
//			int from = (model.getPage() - 1) * size;
//			builder = ESClient.getClent().prepareSearch(index)
//					.setTypes(type).setSearchType(SearchType.DEFAULT)
//					.addSort("@sourceTime", SortOrder.DESC).setFrom(from)
//					.setSize(size);
//		}else{
//			builder = ESClient.getClent().prepareSearch(index)
//					.setTypes(type).setSearchType(SearchType.DEFAULT)
//					.addSort("@sourceTime", SortOrder.DESC).setSize(10000);
//		}
//		BoolQueryBuilder qb = QueryBuilders.boolQuery();
//		//qb.must(QueryBuilders.rangeQuery("B-TSP"));
//		if (model.getStartTime() != null
//				&& !model.getStartTime().trim().equals("")) {
//			qb.must(QueryBuilders.rangeQuery("@sourceTime").from(
//					DateUtil.getDateByString(model.getStartTime())));
//		}
//		if (model.getEndTime() != null && !model.getEndTime().trim().equals("")) {
//			qb.must(QueryBuilders.rangeQuery("@sourceTime").to(
//					DateUtil.getDateByString(model.getEndTime())));
//		}
//		if (model.getBankCode() != null
//				&& !model.getBankCode().trim().equals("")) {
//			qb.must(QueryBuilders.prefixQuery("TLR", model.getBankCode()));
//		}
//		if (model.getTlr() != null && !model.getTlr().trim().equals("")) {
//			qb.must(new QueryStringQueryBuilder(model.getTlr()).field("TLR"));
//		}
//		if (model.getTxd() != null && !model.getTxd().trim().equals("")) {
//			qb.must(new QueryStringQueryBuilder(model.getTxd()).field("TXD"));
//		}
//		if (model.getInmOfflineTxLogNo() != null
//				&& !model.getInmOfflineTxLogNo().trim().equals("")) {
//			qb.must(new QueryStringQueryBuilder(model.getInmOfflineTxLogNo())
//					.field("INM-OFFLINE-TX-LOG-NO"));
//		}
//		if (model.getOpmRespMsVal1() != null
//				&& !model.getOpmRespMsVal1().trim().equals("")) {
//			qb.must(QueryBuilders.rangeQuery("OPM-RESP-MS").from(
//					Integer.parseInt(model.getOpmRespMsVal1())));
//		}
//		if (model.getOpmRespMsVal2() != null
//				&& !model.getOpmRespMsVal2().trim().equals("")) {
//			qb.must(QueryBuilders.rangeQuery("OPM-RESP-MS").to(
//					Integer.parseInt(model.getOpmRespMsVal2())));
//		}
//		if (model.getOpmTxCode() != null
//				&& !model.getOpmTxCode().trim().equals("")) {
//			qb.must(new QueryStringQueryBuilder(model.getOpmTxCode())
//					.field("OPM-TX-CODE"));
//		}
//		if (model.getTxLogNo() != null && !model.getTxLogNo().trim().equals("")) {
//			qb.must(new QueryStringQueryBuilder(model.getTxLogNo())
//					.field("TX-LOG-NO"));
//		}
//
//		String key = model.getKey();
//		if (key != null && !key.trim().equals("")) {
//			for (String k : key.split(",")) {
//				qb.must(QueryBuilders.queryStringQuery("*" + k + "*"));
//			}
//		}
//
//		String condition = "eq";
//		
//		
//		if (model.getField1() != null && !model.getField1().trim().equals("")) {
//			if (model.getCondition1().equals("missing")) {
//				
//				qb.filter(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery(model.getField1())));
//				
//			} else {
//				if (model.getValue1() != null
//						&& !model.getValue1().trim().equals("")) {
//					condition = model.getCondition1();
//					switch (condition) {
//					case "must":
//						qb.must(new QueryStringQueryBuilder(model.getValue1())
//								.field(model.getField1()));
//						break;
//					case "mustNot":
//						qb.mustNot(new QueryStringQueryBuilder(model.getValue1())
//								.field(model.getField1()));
//						break;
//					case "gte":
//						qb.must(QueryBuilders.rangeQuery(model.getField1()).gte(
//								Double.parseDouble(model.getValue1())));
//						break;
//					case "lte":
//						qb.must(QueryBuilders.rangeQuery(model.getField1()).lte(
//								Double.parseDouble(model.getValue1())));
//						break;
//					case "range":
//						String value[] = model.getValue1().split(",");
//						if (value.length > 1) {
//							qb.must(QueryBuilders.rangeQuery(model.getField1())
//									.gte(value[0]).lte(value[1]));
//						}
//						break;
//					default:
//						break;
//					}
//				}
//			}
//			
//		}
//		
//		if (model.getField2() != null && !model.getField2().trim().equals("")) {
//			if (model.getCondition2().equals("missing")) {
//				
//				//qb.must(QueryBuilders.filteredQuery(null, FilterBuilders.missingFilter(model.getField2())));
//				qb.filter(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery(model.getField2())));
//				
//			} else {
//				if (model.getValue2() != null
//						&& !model.getValue2().trim().equals("")) {
//					
//					condition = model.getCondition2();
//					switch (condition) {
//					case "must":
//						qb.must(new QueryStringQueryBuilder(model.getValue2())
//								.field(model.getField2()));
//						break;
//					case "mustNot":
//						qb.mustNot(new QueryStringQueryBuilder(model.getValue2())
//								.field(model.getField2()));
//						break;
//					case "gte":
//						qb.must(QueryBuilders.rangeQuery(model.getField2()).gte(
//								Integer.parseInt(model.getValue2())));
//						break;
//					case "lte":
//						qb.must(QueryBuilders.rangeQuery(model.getField2()).lte(
//								Integer.parseInt(model.getValue2())));
//						break;
//					case "range":
//						String value[] = model.getValue2().split(",");
//						if (value.length > 1) {
//							qb.must(QueryBuilders.rangeQuery(model.getField2())
//									.gte(value[0]).lte(value[1]));
//						}
//						break;
//					default:
//						break;
//					}
//				}
//			}
//			
//		}
//		
//		if (model.getField3() != null && !model.getField3().trim().equals("")) {
//			if (model.getCondition3().equals("missing")) {
//				
//				//qb.must(QueryBuilders.filteredQuery(null, FilterBuilders.missingFilter(model.getField3())));
//				qb.filter(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery(model.getField3())));
//				
//			} else {
//				if (model.getValue3() != null
//						&& !model.getValue3().trim().equals("")) {
//					
//					condition = model.getCondition3();
//					switch (condition) {
//					case "must":
//						qb.must(new QueryStringQueryBuilder(model.getValue3())
//								.field(model.getField3()));
//						break;
//					case "mustNot":
//						qb.mustNot(new QueryStringQueryBuilder(model.getValue3())
//								.field(model.getField3()));
//						break;
//					case "gte":
//						qb.must(QueryBuilders.rangeQuery(model.getField3()).gte(
//								Integer.parseInt(model.getValue3())));
//						break;
//					case "lte":
//						qb.must(QueryBuilders.rangeQuery(model.getField3()).lte(
//								Integer.parseInt(model.getValue3())));
//						break;
//					case "range":
//						String value[] = model.getValue3().split(",");
//						if (value.length > 1) {
//							qb.must(QueryBuilders.rangeQuery(model.getField3())
//									.gte(value[0]).lte(value[1]));
//						}
//						break;
//					default:
//						break;
//					}
//					
//				}
//			}
//			
//		}
//
//		builder.setQuery(qb);
//		SearchResponse response = builder.execute().actionGet();
//		jsonMap.put("total", response.getHits().getTotalHits());
//		SearchHit[] hits = response.getHits().getHits();
//		for (SearchHit hit : hits) {
//			logMap = new HashMap<String, Object>();
//			logMap.put("index", hit.getIndex());
//			logMap.put("id", hit.getId());
//			for (String str : bean.getField().split(",")) {
//				logMap.put(str, hit.getSource().get(str));
//			}
//			list.add(logMap);
//		}
//		jsonMap.put("rows", list);
//		return jsonMap;
//	}
//
//	/**
//	 * 根据索引查看详细日志
//	 *
//	 * @param index
//	 * @param id
//	 * @return
//	 */
//	public static Map<String, Object> getLogbyBtsp(String index, String id) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		Map<String, Object> blogsMap = new HashMap<String, Object>();
//		List<Map<String, Object>> basicList = new ArrayList<Map<String, Object>>();
//		List<Map<String, Object>> sysList = new ArrayList<Map<String, Object>>();
//		List<Map<String, Object>> ccbsList = new ArrayList<Map<String, Object>>();
//		List<Map<String, Object>> optionalList = new ArrayList<Map<String, Object>>();
//		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
//		List<Map<String, Object>> elogList = new ArrayList<Map<String, Object>>();
//		Map<String, List<Map<String, Object>>> basicMap = new HashMap<String, List<Map<String, Object>>>();
//		Map<String, List<Map<String, Object>>> sysMap = new HashMap<String, List<Map<String, Object>>>();
//		Map<String, List<Map<String, Object>>> ccbsMap = new HashMap<String, List<Map<String, Object>>>();
//		Map<String, List<Map<String, Object>>> optionalMap = new HashMap<String, List<Map<String, Object>>>();
//		Map<String, List<Map<String, Object>>> dataMap = new HashMap<String, List<Map<String, Object>>>();
//		Map<String, List<Map<String, Object>>> elogsMap = new HashMap<String, List<Map<String, Object>>>();
//		Map<String, Object> tmpMap;
//		Object txd = null, inmTxTyp = null;
//		GetResponse responseGet = ESClient.getClent()
//				.prepareGet(index, type, id).execute().actionGet();
//		blogsMap = responseGet.getSourceAsMap();
//		// 格式化base-info
//		for (String key : esMap.get("btsp-base-info").split(",")) {
//			if (blogsMap.get(key) != null) {
//				if (key.equals("TXD")) {
//					txd = blogsMap.get(key);
//					txd = cmtxpMap.containsKey(txd) ? cmtxpMap.get(txd) : txd; // 处理可复用TXD
//				}
//				tmpMap = new HashMap<String, Object>();
//				tmpMap.put("key", key);
//				tmpMap.put("value", blogsMap.get(key));
//				basicList.add(tmpMap);
//				blogsMap.remove(key);
//			}
//		}
//		basicMap.put("rows", basicList);
//
//		if (txd.equals("CBODTCCR")) { // 大事物输出类型采用特殊处理
//			for (String key : esMap.get("trans-header").split(",")) {
//				if (blogsMap.get(key) != null) {
//					tmpMap = new HashMap<String, Object>();
//					tmpMap.put("key", key);
//					tmpMap.put("value", blogsMap.get(key));
//					sysList.add(tmpMap);
//					blogsMap.remove(key);
//				}
//			}
//			sysMap.put("rows", sysList);
//		} else {
//			// 格式化sys-header
//			for (String key : esMap.get("sys-header").split(",")) {
//				if (blogsMap.get(key) != null) {
//					tmpMap = new HashMap<String, Object>();
//					tmpMap.put("key", key);
//					tmpMap.put("value", blogsMap.get(key));
//					sysList.add(tmpMap);
//					blogsMap.remove(key);
//				}
//			}
//			sysMap.put("rows", sysList);
//
//			// 格式化ccbs-header
//			for (String key : esMap.get("ccbs-header").split(",")) {
//				if (blogsMap.get(key) != null) {
//					if (key.equals("INM-TX-TYP")) {
//						inmTxTyp = blogsMap.get(key);
//					}
//					tmpMap = new HashMap<String, Object>();
//					tmpMap.put("key", key);
//					tmpMap.put("value", blogsMap.get(key));
//					ccbsList.add(tmpMap);
//					blogsMap.remove(key);
//				}
//			}
//			ccbsMap.put("rows", ccbsList);
//
//			if ("1".equals(inmTxTyp)) {// 处理更正交易
//				// 格式化optional-input-header
//				int i = 0;
//				for (String key : esMap.get("optional-input-header").split(",")) {
//					i++;
//					if (esMap.get("rewars-optional-header").contains(key)
//							&& blogsMap.get(key) != null) {
//						tmpMap = new HashMap<String, Object>();
//						tmpMap.put("key", key);
//						tmpMap.put("no", i);
//						tmpMap.put("value", StringFilter(blogsMap.get(key).toString()));
//						optionalList.add(tmpMap);
//						blogsMap.remove(key);
//					}
//				}
//				optionalMap.put("rows", optionalList);
//				// 格式化DAT输出
//				String[] rewarsTransactionData = esMap.get(
//						"rewars-transaction-data").split(",");
//				String[] rewarsTransactionDesc = esMap.get(
//						"rewars-transaction-data-desc").split(",");
//				for (int j = 0; j < rewarsTransactionData.length; j++) {
//					if (blogsMap.get(rewarsTransactionData[j]) != null) {
//						tmpMap = new HashMap<String, Object>();
//						tmpMap.put("name", rewarsTransactionData[j]);
//						tmpMap.put("length",
//								blogsMap.get(rewarsTransactionData[j])
//										.toString().length());
//						tmpMap.put("value",
//								blogsMap.get(rewarsTransactionData[j]));
//						tmpMap.put("desc", rewarsTransactionDesc[j]);
//						dataList.add(tmpMap);
//						blogsMap.remove(rewarsTransactionData[j]);
//					}
//				}
//				dataMap.put("rows", dataList);
//			} else {
//				// 格式化optional-input-header
//				int i = 0;
//				for (String key : esMap.get("optional-input-header").split(",")) {
//					i++;
//					if (blogsMap.get(key) != null) {
//						tmpMap = new HashMap<String, Object>();
//						tmpMap.put("key", key);
//						tmpMap.put("no", i);
//						tmpMap.put("value", StringFilter(blogsMap.get(key).toString()));
//						optionalList.add(tmpMap);
//						blogsMap.remove(key);
//					}
//				}
//				optionalMap.put("rows", optionalList);
//
//				// 格式化DAT输出
//				if (tdtMap.containsKey(txd)) {
//					List<Map<String, Object>> list = tdtMap.get(txd);
//					for (Map<String, Object> txMap : list) {
//						tmpMap = new HashMap<String, Object>();
//						tmpMap.put("name", txMap.get("name"));
//						if (blogsMap.get(txMap.get("name")) == null) {
//							continue;
//						} else {
//							String tmpStr = StringFilter(blogsMap.get(txMap.get("name")).toString());
//							tmpMap.put("length", tmpStr.length());
//							tmpMap.put("value", tmpStr);
//						}
//						dataList.add(tmpMap);
//						blogsMap.remove(txMap.get("name"));
//					}
//					dataMap.put("rows", dataList);
//				} else {
//					logger.warn("[" + txd + "] not found in TDT......");
//				}
//			}
//		}
//		if (blogsMap.containsKey("E-TSP")) {
//			String[] strs = {"@sourceTime","@timestamp","@version","LEN","startTag","type"};
//			for (String key : blogsMap.keySet()) {
//				if(Arrays.asList(strs).contains(key)){
//					continue;
//				}
//				tmpMap = new HashMap<String, Object>();
//				tmpMap.put("key", key);
//				tmpMap.put("value", StringFilter(blogsMap.get(key).toString()));
//				elogList.add(tmpMap);
//			}
//			elogsMap.put("rows", elogList);
//		}
//
//		map.put("basic", basicMap);
//		map.put("sys", sysMap);
//		map.put("ccbs", ccbsMap);
//		map.put("optional", optionalMap);
//		map.put("data", dataMap);
//		map.put("eTsp", elogsMap);
//		return map;
//	}
//	
//	public static String StringFilter(String str) throws PatternSyntaxException {     
//        // 只允许字母和数字       
//        // String   regEx  =  "[^a-zA-Z0-9]";                     
//           // 清除掉所有特殊字符  
//	  String regEx="[^a-zA-Z0-9 #-_]";  
//	  Pattern   p   =   Pattern.compile(regEx);     
//	  Matcher   m   =   p.matcher(str);     
//	  return   m.replaceAll("").trim();     
//	} 
//
//	public static void main(String[] args) {
//		Settings settings = Settings.settingsBuilder()
//				//.put("client.transport.sniff", true)
//				.put("cluster.name", "hippo").build();
//		
//		try {
////			Class<?> clazz = Class.forName(TransportClient.class.getName());
////			Constructor<?> constructor = clazz.getDeclaredConstructor(Settings.class);
////			constructor.setAccessible(true);
////			client = (TransportClient)constructor.newInstance(settings);
////			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.12.198.240"), 9300));
//			
//			Client client = TransportClient.builder().settings(settings).build()  
//	                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.12.198.240"), 9300)); 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
