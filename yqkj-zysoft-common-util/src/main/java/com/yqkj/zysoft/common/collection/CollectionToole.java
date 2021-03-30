package com.yqkj.zysoft.common.collection;

import com.yqkj.zysoft.common.constants.CommonConstant;
import com.yqkj.zysoft.common.string.StringUtil;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import static com.yqkj.zysoft.common.constants.CommonConstant.LOSS_ONE_LONG;

/**
 * @author yangchao.cool@gmail.com
 * @copyright: Copyright (c) 2020
 * @company: 扬起科技有限公司
 * @date 2020/9/27 17:24
 * @description:
 */
public final class CollectionToole {

    private CollectionToole() {
    }
    /**
     * 转换数组数据
     * @param arrray 字符数组
     * @return 字符串集合
     */
    public  static  List<String> convertToArray(final String[] arrray) {
        List<String> result = new ArrayList<>(arrray.length);
        for (int index = 0; index < arrray.length; index++) {
            if (index == 0) {
                arrray[0] = arrray[0];
            } else {
                arrray[index] = arrray[index - 1] + "|" + arrray[index];
            }
            result.add(arrray[index]);
        }
        return  result;
    }
    /**
     * 集合长度
     * @param list 集合
     * @return 集合长度
     */
    public  static  Integer listSize(final List list) {
        if (CollectionToole.isNull(list)) {
            return  0;
        }
        return  list.size();
    }

    /**
     * 数组转换为List集合
     * @param array 数组
     * @return List
     */
    public  static  List<Long>  convertToArray(final Long[] array) {
        List<Long> idList = Arrays.asList(array);
        return  idList;
    }

    /**
     * @param list 集合列表
     * @param fn 函数引用
     * @param <O> 对象
     * @return 字符数组
     */
    public  static <O> String[] parseIdsArray(final List<O> list, final Function<O, String> fn) {
        if (!isNull(list)) {

            String[] ar = new String[list.size()];

            for (int index = 0, count = list.size(); index < count; index++) {
                ar[index] = fn.apply(list.get(index));
            }

            return  ar;
        }
        return null;

    }
    /**
     * 切割
     * @param source 数据集合
     * @param n 每个集合数据长度条数
     * @param <T> 集合对象
     * @return 切分号的对象集合
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remaider = source.size() % n;
        //(先计算出余数)
        int number = source.size() / n;
        //然后是商
        int offset = 0;
        //偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }
    /**
     * @Description:
     * @param list 集合
     * @param group 分组Key
     * @param <K> k
     * @param <O> 集合对象
     * @return Map<K,List<O>> 分组后集合
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年6月13日 上午9:31:30
     */
    public static <K, O>  Map<K, List<O>> groupList(List<O> list, Function<O, K> group) {
        if (null == list) {
            return  new HashMap<>();
        }
        return  list.stream().collect(Collectors.groupingBy(group));
    }
    /**
     * 求平均值
     *
     * @Description:
     * @param list 集合
     * @param fn 引用
     * @param <T> 对象
     * @return Double 浮点型
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年6月11日 下午4:55:09
     */
    public static <T> Double avgDouble(List<T> list, ToDoubleFunction<T> fn) {
        if (isNull(list)) {
            return 0D;
        }
        return list.stream().mapToDouble(fn).average().getAsDouble();
    }
    /**
     * @param ids 集合
     * @return Stirng 以“，”连接好的字符串
     */
    public  static  String convertToStringStr(List<String> ids) {
        StringBuffer stringBuffer = new StringBuffer();
        if (!CollectionToole.isNull(ids)) {
            ids.forEach(id -> {
                stringBuffer.append(id).append(",");
            });
            return  stringBuffer.substring(0, stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }
    /**
     *
     * @ClassName: CollectionToole
     * @Description:
     * @param  ids id集合
     * @return  拼接好的字符串
     * @author yangchao.coo@gmail.com
     * @date 2020/1/8
     *
     */
    public  static  String convertToString(List<Long> ids) {
        StringBuffer stringBuffer = new StringBuffer();
        if (!CollectionToole.isNull(ids)) {
            ids.forEach(id -> {
                stringBuffer.append(id).append(",");
            });
            return  stringBuffer.substring(0, stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }
    /**
     *
     *
     * @Description:
     * @param ids 以“，”隔开连接好的字符串
     * @return List<Long> Long的集合
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年6月11日 上午10:51:15
     */
    public static List<Long> parseIds(String ids) {
        if (StringUtil.isNotBlank(ids)) {
            String[] ar = ids.split(",");
            List<Long> result = new ArrayList<>(ar.length);
            for (String n: ar) {
                try {
                    if (StringUtil.isNotBlank(n)) {
                        result.add(Long.valueOf(n));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
        return null;
    }
    /**
     * @Description:跟一个集合执行对象中的一个方法
     * @param list 集合
     * @param <T>  参数
     * @param  <R> 参数
     * @param fn 函数引用
     * @return
     * @exception:
     * @author: yangchao.coo@gmail.com
     * @time:2019年6月10日 上午10:09:39
     */
    public static <T, R>  void excuteMethod(List<T> list, Function<T, R> fn) {
        if (isNull(list)) {
            return;
        }
        list.forEach((T ob) -> {
            fn.apply(ob);
        });
    }
    /**
     * 跟一个值另一个值
     * @param list 集合
     * @param fn 函数引用
     * @param consumer 消费函数
     * @param map Map 集合
     * @param <T> 对象
     * @param <K> key
     * @param <V> 值
     * @return 返回集合
     */
    public  static <T, K, V> List<T> initOneInfo(List<T> list,
                                                 Function<T, K> fn,
                                                 Function<T, Consumer<V>> consumer,
                                                 Map<K, V> map) {
        if (null == list || null == map) {
            return list;
        }
        for (T t: list) {
            K key = fn.apply(t);
            if (map.containsKey(key)) {
                V value =  map.get(key);
                consumer.apply(t).accept(value);
            } else {
                V value =  null;
                consumer.apply(t).accept(value);
            }
        }
        return list;
    }
    /**
     * 过滤数据
     *
     * @Description:
     * @param list 集合
     * @param pre 过滤
     * @param  <T> 入参
     * @return List<T> 集合
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年6月4日 上午10:21:24
     */
    public  static <T> List<T> filterList(List<T> list, Predicate<T> pre) {
        if (isNull(list)) {
            return new ArrayList<>();
        }
        return list.stream().filter(pre).collect(Collectors.toList());
    }
    /**
     * @Description:检查数组是否为空 如果为空则返回空集合
     * @param list 集合
     * @return List<T> 返回集合 ， 如果为空 则返回不可以修改的空集合
     * @exception:
     * @param <T> 入参
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月28日 下午5:00:34
     */
    public  static <T> List<T> checkListAndReturnEmptyList(List<T> list) {
        if (isNull(list)) {
            return CommonConstant.EMPTY_LIST;
        }
        return list;
    }
    /**
     * @Description:
     * @param list 集合
     * @param fn 函数引用
     * @param <T> 入参
     * @return Double 返回Double
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月27日 上午10:35:12
     */
    public static <T> Double sumDouble(List<T> list, Function<T, Double> fn) {
        if (isNull(list)) {
            return Double.valueOf(0);
        }
        try {
            return list.stream().map(fn).reduce(Double::sum).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Double.valueOf(0);
    }
    /**
     * @Description: list集合求和
     * @param list 集合
     * @param fn 函数引用
     * @param <T> 入参
     * @return Integer 返回Int类型
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月27日 上午10:33:21
     */
    public static <T> Integer sumInteger(List<T> list, Function<T, Integer> fn) {
        if (isNull(list)) {
            return Integer.valueOf(0);
        }
        List<Integer> longList = list.stream().map(fn).filter(num -> null != num).collect(Collectors.toList());
        return longList.stream().reduce(Integer::sum).get();
    }
    /**
     * @Description:
     * @param list 数据集合
     * @return List<T> 返回数据反转集合
     * @param  <T> 入参
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月27日 上午10:28:46
     */
    public static <T> List<T> listRever(List<T> list) {
        if (isNull(list)) {
            return CommonConstant.EMPTY_LIST;
        }
        List<T> result = new ArrayList<>(list.size());
        for (int index = result.size(); index > 0; --index) {
            result.add(list.get(index));
        }
        return result;
    }
    /**
     * @Description: 降序
     * @param list  数据集合
     * @param cm void
     * @param <O> 对象
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月22日 上午9:28:33
     */
    public static <O> void sortDesc(List<O> list, Comparator<O> cm) {
        if (null == cm) {
            return;
        }
        sort(list, cm.reversed());
    }
    /**
     * @Description:降序
     * @param list 数据集合
     * @param cm void
     * @param  <O> 对象
     * @return  集合
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月22日 上午9:28:33
     */
    public static <O> List<O> sort(List<O> list, Comparator<O> cm) {
        if (Objects.isNull(list) || list.isEmpty()) {
            return list;
        }
        return list.stream().sorted(cm).collect(Collectors.toList());
    }
    /**
     * List<Object> 转换成Map<String , Object>
     * @param list 数据集合
     * @param <K> k 方法引用
     * @param <T> t 方法引用
     * @param  <V> 值
     * @param keyFn key 函数引用
     * @param  valueFn 值函数引用
     * @return map
     */
    public static  <K, T, V> Map<K, V> convertMap(Set<T> list, Function<T, K> keyFn, Function<T, V> valueFn) {
        Map<K, V> result = new HashMap<>(list.size());
        if (null == list || list.isEmpty()) {
            return  CommonConstant.EMPTY_MAP;
        }
        for (T ob : list) {
            result.put(keyFn.apply(ob), valueFn.apply(ob));
        }
        return  result;
    }
    /**
     * 集合转换为Map
     * @param list 集合
     * @param keyFn Key 函数引用
     * @param valueFn 值函数引用
     * @param <K> 类型
     * @param <T> 类型
     * @param <V> 裂隙
     * @return Map
     */
    public static  <K, T, V> Map<K, V> convertMap(List<T> list, Function<T, K> keyFn, Function<T, V> valueFn) {
        Map<K, V> result = new HashMap<>(list.size());
        if (null == list || list.isEmpty()) {
            return  CommonConstant.EMPTY_MAP;
        }
        for (T ob : list) {
            result.put(keyFn.apply(ob), valueFn.apply(ob));
        }
        return  result;
    }
    /**
     * 列表转为另一个列表
     * @param list 集合
     * @param fn 函数
     * @param <T> 类型
     * @param <R> 类型
     * @return 返回Set
     */
    public  static  <T, R> Set<R> convertToSet(List<T> list, Function<T, R> fn) {
        if (Objects.isNull(list) || list.isEmpty()) {
            return new HashSet<>();
        }
        Set<R> resutlt = new HashSet<>(list.size());
        for (T t : list) {
            resutlt.add(fn.apply(t));
        }
        return  resutlt;
    }
    /**
     * 列表转为另一个列表
     * @param list 集合
     * @param fn 函数应用
     * @param <T> 类型
     * @param <R> 值类型
     * @return 返回List集合
     */
    public  static  <T, R> List<R> convertToList(Set<T> list, Function<T, R> fn) {
        if (Objects.isNull(list) || list.isEmpty()) {
            return new ArrayList<>();
        }
        return  list.stream().map(fn).collect(Collectors.toList());
    }
    /**
     * 列表转为另一个列表
     * @param list 集合
     * @param fn 值函数
     * @param <T> 类型
     * @param <R> 类型
     * @return 返回List集合
     */
    public  static  <T, R> List<R> convertToList(List<T> list, Function<T, R> fn) {
        if (Objects.isNull(list) || list.isEmpty()) {
            return new ArrayList<>();
        }
        return  list.stream().map(fn).collect(Collectors.toList());
    }
    /**
     * 对象拷贝
     * @Description:
     * @param list 集合
     * @param r 入参
     * @param  <O> 类型
     * @param <R> 类型
     * @return List<R> 集合
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月16日 下午6:16:17
     */
    public static <R, O> List<R> convertToObjectList(List<O> list, Class< R> r) {
        if (isNull(list)) {
            return (List<R>) new ArrayList<>();
        }
        List<R> result = new ArrayList<>(list.size());
        for (O dto : list) {
            result.add(convertToObject(dto, r));
        }
        return result;
    }
    /**
     * 对象拷贝
     * @Description:
     * @param <O> 拷贝对象
     * @param o 参数对象
     * @param  r  参数
     * @param <R> 类型Class
     * @return R 返回转换类型
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月16日 下午6:18:43
     */
    public static <O, R> R convertToObject(O o, Class< R> r) {
        R temp = null;
        copyObject(o, temp);
        return temp;
    }
    /**
     * @param <O> 拷贝对象
     * @param <R> 类型Class
     * @Description:
     * @param source 源
     * @param target 目标
     * @exception:
     * @throws
     * @author: yangchao.coo@gmail.com
     * @time:2019年5月22日 上午9:15:09
     */
    public static <R, O> void copyObject(R source, O target) {
        if (Objects.isNull(source)) {
            return;
        }
    }
    /**
     * @param t 集合参数
     * @return true 集合为空 ， 否则集合为非空
     */
    public  static Boolean isNull(Collection t) {
        if (Objects.isNull(t) || t.isEmpty()) {
            return  Boolean.TRUE;
        }
        return Boolean.FALSE;

    }
    /**
     * @param t 入参Map
     * @return 是否为空
     */
    public  static  Boolean isNull(Map t) {
        if (Objects.isNull(t) || t.isEmpty()) {
            return  Boolean.TRUE;
        }
        return Boolean.FALSE;

    }
    /**
     * 数组为空
     * @param t 集合
     * @param <T> 类型
     * @return 如果为 true 集合为空 ， 否则集合为非空
     */
    public  static<T>  Boolean isNull(T[] t) {
        if (null == t || t.length <= 0) {
            return  Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * Set 集合是否为空
     * @param t 集合
     * @param <T> 类型
     * @return 如果为 true 集合为空 ， 否则集合为非空
     */
    public  static<T>  Boolean isNull(Set<T> t) {
        if (null == t || t.isEmpty()) {
            return  Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * List 集合是否为空
     * @param t 集合
     * @param <T> 类型
     * @return  如果为 true 集合为空 ， 否则集合为非空
     */
    public  static<T>  Boolean isNull(List<T> t) {
        if (null == t || t.isEmpty()) {
            return  Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    /**
     * 解析Long 集合
     * @param list 入参集合
     * @return 返回一个List集合
     */
    public  static  List<Long> requirOneDataList(List<Long> list) {
        if (CollectionToole.isNull(list)) {
            List<Long> ins = new ArrayList<>();
            ins.add(LOSS_ONE_LONG);
            return  ins;
        }
        return list;
    }
    /**
     * 对象转map
     * @param obj 对象
     * @return 返回List
     * @throws Exception 异常
     */
    public static Map<String, Object> convertToMap(Object obj) throws Exception {
        Map<String,  Object> map = new HashMap<String, Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return  map;
    }
}
