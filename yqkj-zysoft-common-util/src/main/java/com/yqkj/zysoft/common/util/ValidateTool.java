package com.yqkj.zysoft.common.util;



/**
  *
  * @ClassName: ValidateTool
  * @Description:
  * @author yangchao.coo@gmail.com
  * @date 2019/12/30
  *
 */
public class ValidateTool {
/**
 * @Param 
 * @author yangchao.cool@gmail.comd
 * @date 2020/9/27 15:40
 * @description:
 *//*
    public  static  <O> void  assertRepeatObject(O dbObject , O object , Function<O , Long> eqFn, String msg){
        *//**
         * 如果数据存在数据 ， 对象数据为空那么提示
         *//*
        if(!Objects.isNull(dbObject) && Objects.isNull(object)){
            Param.error(StringUtils.isBlank(msg)?"不能新增重复数据":msg);
        }
        *//**
         * 为空表示数据在数据库不在
         *//*
        if(Objects.isNull(dbObject)){
            return;
        }
        *//**
         * 如果DB不为空 则表示数据库存在，则判断数据ID是否相等 ， 如果不相等则抛出异常
         *//*
        if(eqFn.apply(dbObject).compareTo(eqFn.apply(object)) != 0){
            Param.error(StringUtils.isBlank(msg)?"不能新增重复数据":msg);
        }
    }
    *//**
     *
     * @param r
     * @param list
     * @param <T>
     * @param <R>
     *//*
    public static   <T,R>   void isEqrrayAndList(R[] r , List<T> list){
        if(CollectionToole.isNull(list) || Objects.isNull(r) || r.length <=0 ){
            Param.error("参数非法!");
        }
        if(list.size() != r.length){
            Param.error("参数发非法,不能操作") ;
        }
    }

    *//**
     * 判断是否是纯数字
     * @param str
     * @return
     *//*
    public static boolean isNumeric(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }
    *//**
     * 判断参数是否为空
     * @param param
     * @param error
     *//*
    public static void validataObjectIsNull(Object param , String error) {
        if(Objects.isNull(param)) {
            Param.error(StringUtils.isBlank(error)?"参数非法,为空":error);
        }
    }*/
}
