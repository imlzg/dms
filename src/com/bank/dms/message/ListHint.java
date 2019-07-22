package com.bank.dms.message;

public class ListHint {
    
    public static final String NOTFIND = 
        "<table style=\"color:#333; font-size:12px;text-align:left;margin:10px 0 0 10px;\" align=\"left\">" +
            "<tr align=\"left\">" +
                "<td><strong>抱歉，没有找到您查询的相关信息。</strong></td>" +
            "</tr>" +
            "<tr align=\"left\" height=\"30\">" +
                "<td><br>您还可以:</td>" +
            "</tr>" +
            "<tr align=\"left\" height=\"30\">" +
                "<td>1. 检查输入的文字是否有误</td>" +
            "</tr>" +
            "<tr align=\"left\" height=\"30\">" +
                "<td>2. 尝试更换不同的查询条件</td>" +
            "</tr>" +
        "</table>";

}
