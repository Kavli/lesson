package com.apebug.main;

import com.apebug.entity.Ele;
import com.apebug.entity.EleLine;

import java.util.*;

public class LoopCheckMain {
    public static void main(String[] args){
        //构建测试图
        List<EleLine> lines = new ArrayList<EleLine>();
        lines.add(new EleLine(1L, 3L));
        lines.add(new EleLine(3L, 5L));
        lines.add(new EleLine(5L, 7L));
        lines.add(new EleLine(2L, 3L));
        lines.add(new EleLine(3L, 6L));
        lines.add(new EleLine(6L, 7L));
        lines.add(new EleLine(2L, 4L));
        //lines.add(new EleLine(6L, 2L));

        //算法1
        List<Long> loopIds = checkLoopWithTraversal(lines);
        if (loopIds != null && loopIds.size() > 0) {
            System.out.println("NG");
        } else {
            System.out.println("OK");
        }

        //算法2
        if (checkLoopWithTopology(lines)) {
            System.out.println("Ok");
        } else {
            System.out.println("NG");
        }
    }

    private static boolean checkLoopWithTopology(List<EleLine> lines) {
        List<EleLine> checkLines = new ArrayList<EleLine>(lines);
        do {
            int size = checkLines.size();
            removeFromOnlyEle(checkLines);
            if (size == checkLines.size()) {
                return false;
            }
        } while (checkLines.size() > 0);
        return true;
    }

    public static List<Long> checkLoopWithTraversal(List<EleLine> lines){
        //找出所有终止结点
        List<Long> finalIds = getFinalIds(lines);
        //整理结点关系图，构建结点对象
        Map<Long, Ele> allEleMap = new HashMap<Long, Ele>();
        for(EleLine line: lines) {
            Ele toEle = allEleMap.get(line.getToEleId());
            if (toEle == null) {
                toEle = new Ele(line.getToEleId());
                allEleMap.put(toEle.getId(), toEle);
            }
            Ele fromEle = allEleMap.get(line.getFromEleId());
            if (fromEle == null) {
                fromEle = new Ele(line.getFromEleId());
                allEleMap.put(fromEle.getId(), fromEle);
            }
            toEle.getFromEleList().add(fromEle);
        }
        for(Long id: finalIds) {
            //定义set 存放某一条路径的所有id
            Set<Long> pathIds = new HashSet<Long>();
            //从终止结点出发遍历
            Long resultEleId = checkLoopEle(pathIds, allEleMap.get(id));
            if (resultEleId != null) {
                //有问问题
                return new ArrayList<Long>(pathIds);
            }
        }
        return null;
    }

    /**
     * 移除所有的终止结点
     * @param checkLines
     */
    private static void removeFromOnlyEle(List<EleLine> checkLines) {
        Set<Long> fromIds = new HashSet<Long>();
        for (EleLine line : checkLines) {
            fromIds.add(line.getFromEleId());
        }
        for (int i=0; i<checkLines.size(); i++) {
            EleLine line = checkLines.get(i);
            if (!fromIds.contains(line.getFromEleId()) || !fromIds.contains(line.getToEleId())) {
                checkLines.remove(i);
                i--;
            }
        }
    }

    /**
     * 嵌套递归调用
     * @param pathIds
     * @param currentEle
     * @return
     */
    private static Long checkLoopEle(Set<Long> pathIds, Ele currentEle) {
        if (pathIds.contains(currentEle.getId())) {
            return currentEle.getId();
        }
        if (currentEle.getFromEleList().size() == 0) {
            //路径到底，无循环
            return null;
        }
        Long result;
        pathIds.add(currentEle.getId());
        for (Ele ele : currentEle.getFromEleList()) {
            result = checkLoopEle(pathIds, ele);
            if (result != null) {
                return result;
            }
        }
        //该结点下所有子结点检查完毕无循环，将该结点从路径id集合删除
        pathIds.remove(currentEle.getId());
        return null;
    }

    /**
     * 找出终止结点
     * @param lines
     * @return
     */
    public static List<Long> getFinalIds(List<EleLine> lines){
        Set<Long> fromIds = new HashSet<Long>();
        Set<Long> allIds = new HashSet<Long>();
        for (EleLine line : lines) {
            allIds.add(line.getFromEleId());
            allIds.add(line.getToEleId());
            fromIds.add(line.getFromEleId());
        }
        for (Long id : fromIds) {
            allIds.remove(id);
        }
        return new ArrayList<Long>(allIds);
    }
}
