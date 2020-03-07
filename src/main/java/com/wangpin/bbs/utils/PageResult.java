package com.wangpin.bbs.utils;

public class PageResult {

    public static int[] createPage(int totalPage,int page){
        int[] pageResult={-1,-1,-1,-1,-1};
        if (page>totalPage-1)
            page=totalPage-1;
        if (page<0)
            page=0;
        int remainingPages=totalPage-page;
        if (page<4&&totalPage<=4)
            for (int i=0;i<totalPage;i++) {
                pageResult[i]=i;
            }
        if (page<4&&totalPage>4)
            for (int i=0;i<pageResult.length;i++) {
                pageResult[i]=i;
            }
        if (page>=4&&remainingPages>2)
            for (int i=0;i<pageResult.length;i++) {
                pageResult[i]=page+(i-2);//-2 -1 0 1 2
            }
        if (page>=4&&remainingPages<=2)
            for (int i=0;i<pageResult.length;i++) {
                pageResult[i]=totalPage-(4-i)-1;//-2 -1 0 1 2
            }

        return pageResult;
    }
}
