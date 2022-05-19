package com.ocean.sever.config;

import java.util.List;

/**
 * @author back
 */
public class Page {

    public static <T> ResultList<T> Page(Integer pageNum, Integer pageSize, List<T> sourceList, Boolean isPage){
        ResultList<T> result = new ResultList<>();
        result.setTotal(0);
        List<T> source = sourceList;

        if(source!=null&&!source.isEmpty()){
            int sourceSize = source.size();
            if (isPage){
                pageNum = (pageNum == null || pageNum <= 0) ? 1 : pageNum;
                pageSize = (pageSize == null || pageSize <= 0) ? 10 : pageSize;
                source = sourceList.subList(Math.min((pageNum - 1) * pageSize, sourceSize), Math.min(pageNum * pageSize, sourceSize));
            }
            result.setList(source);
            result.setTotal(sourceSize);
            }
        return result;
    }
}