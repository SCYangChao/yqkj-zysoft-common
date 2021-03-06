package com.yqkj.zysoft.common.cache;
/**
 * @ClassName ICacheProcessor
 * @Description
 * @Author yangchao.cool@gmail.com
 * @Date 2021/1/25 15:20
 * @Version 1.0
 **/
public interface ICacheProcessor {
    /**
     * 缓存数据接口
      * @param key key
     * @param value value
     * @param  time -1 没有失效时间
     */
   public void cache(String key, Object value, int time);
    /**
     * 缓存数据接口
     * @param key key
     *
     */
    public void evict(String key);
    /**
     * 获取缓存
     * @param key key
     * @return  对象
     */
    public Object get(String key);

}
