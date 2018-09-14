<%@ page language="java" pageEncoding="utf-8" import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Calendar</title>
    <script type="text/javascript" src="jquery.min.js"></script>
    <%----%>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
   <%-- <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>--%>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="js/bootstrap.min.js"></script>
    <%----%>



  <style type="text/css">
    body{
      background:#f2f2f2;
      margin:40px;
    }
    *{
      margin:0;
      padding:0;
    }
    .calendar{
      width:450px;
      height:350px;
      background:#fff;
      box-shadow:0px 1px 1px rgba(0,0,0,0.1);
    }
    .title{
      height:70px;
      border-bottom:1px solid rgba(0,0,0,0.1);
      text-align:center;
      position:relative;
    }
    #calendar-title{
      font-size:25px;
      font-family:arial;
      font-weight:bold;
      text-transform:uppercase;
      padding:14px 0 0 0;
    }
    #calendar-year{
      font-size:15px;
      font-family:arial;
      font-weight:normal;
    }
    #prev{
      text-indent:-9999px;
      position:absolute;
      left:0;
      top:0;
      width:60px;
      height:70px;
      background:url(prev.png) no-repeat 50% 50%;
    }
    #next{
      text-indent:-9999px;
      position:absolute;
      right:0;
      top:0;
      width:60px;
      height:70px;
      background:url(next.png) no-repeat 50% 50%;
    }
    .body{
      padding:10px 20px;
    }
    .body-list ul{
      width:100%;
      font-family:arial;
      font-weight:bold;
      font-size:14px;
    }
    .body-list ul li{
      width:14.28%;
      height:36px;
      line-height:36px;
      list-style-type:none;
      display:block;
      box-sizing:border-box;
      float:left;
      text-align:center;
    }
    .lightgrey{
      color:#a8a8a8;
    }
    .darkgrey{
      color:#565656;
    }
    .green{
      color:#6ac13c;
    }
    .greenbox{
      border:1px solid #6ac13c;
      background:#e9f8df;
    }
    .geta{
      width: 14.28%;
      height: 36px;
      line-height: 36px;
      list-style-type: none;
      display: block;
      box-sizing: border-box;
      float: left;
      text-align: center;
      border:3px solid #e7f510;
      text-align: center;
      align-items: center;
    }


  </style>
</head>

<body>
<div class="calendar">
  <div class="title">
    <h1 class="green" id="calendar-title">Month</h1>
    <h2 class="green small" id="calendar-year">Year</h2>
    <a href="" id="prev">Prev Month</a>
    <a href="" id="next">Next Month</a>
  </div>
  <div class="body">
    <div class="lightgrey body-list" >
      <ul>
       <%-- <li>MON</li>
        <li>TUE</li>
        <li>WED</li>
        <li>THU</li>
        <li>FRI</li>
        <li>SAT</li>
        <li>SUN</li>
        --%>
        <li>星期一</li>
        <li>星期二</li>
        <li>星期三</li>
        <li>星期四</li>
        <li>星期五</li>
        <li>星期六</li>
        <li>星期日</li>

      </ul>
    </div>
    <div class="darkgrey body-list">
      <ul id="days">
      </ul>
    </div>
  </div>
</div>
<hr width="450px"/>
<div style="width: 450px" class="darkgrey">

  <ul id="recodeList" class="list-unstyled" style="align-items: center">

   <%-- <c:forEach items="${user}" var="user"></c:forEach>--%>
  </ul>
</div>
<script type="text/javascript">
    var month_olympic = [31,29,31,30,31,30,31,31,30,31,30,31];
    var month_normal = [31,28,31,30,31,30,31,31,30,31,30,31];

   // var month_name = ["January","Febrary","March","April","May","June","July","Auguest","September","October","November","December"];
    var month_name = ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"];
    var holder = document.getElementById("days");
    var prev = document.getElementById("prev");
    var next = document.getElementById("next");
    var ctitle = document.getElementById("calendar-title");
    var cyear = document.getElementById("calendar-year");
    var my_date = new Date();
    var my_year = my_date.getFullYear();
    var my_month = my_date.getMonth();
    var my_day = my_date.getDate();
    prev.onclick = function(e){
        e.preventDefault();
        my_month--;
        if(my_month<0){
            my_year--;
            my_month = 11;
        }
        refreshDate();
    }
    next.onclick = function(e){
        e.preventDefault();
        my_month++;
        if(my_month>11){
            my_year++;
            my_month = 0;
        }
        refreshDate();
    }
    function showMenu() {
        jQuery.post("/add.do",{"recordsDate":new Date(),"userId":1}, function (date) {
            alert(date.message);
        },"json")

    }
    function showRecodrs(doc,i) {
        $(".geta").removeClass("geta");
        $(".darkgrey").each(function (va,vb,vc) {
            vb.style.backgroundColor="#ffffff";
        })
        $(".lightgrey").each(function (va,vb,vc) {
            vb.style.backgroundColor="#ffffff";
        })
          $(".green.greenbox").each(function (va,vb,vc) {
            vb.style.backgroundColor="#e9f8df";
        })



        $(doc).addClass("geta");

        jQuery.post("/getTodayList.do",{"recordsDate":i,"userId":1}, function (date) {
            var htmls = "";
            $("#recodeList").html("");
            htmls+=" <li><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;今日打卡记录</b></li>";
            $(date).each(function (index,r) {
               // alert(index+"--"+r.time);
                htmls+="<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+r.time+"</li>";

            })
            $("#recodeList").html(htmls);
        },"json")
    }
    function refreshDate(){
        var str = "";
        var totalDay = daysMonth(my_month, my_year); //获取该月总天数
        var firstDay = dayStart(my_month, my_year); //获取该月第一天是星期几
        var myclass;
        for(var i=1; i<firstDay; i++){
            str += "<li></li>"; //为起始日之前的日期创建空白节点
        }
        for(var i=1; i<=totalDay; i++){
            if((i<my_day && my_year==my_date.getFullYear() && my_month==my_date.getMonth()) || my_year<my_date.getFullYear() || ( my_year==my_date.getFullYear() && my_month<my_date.getMonth())){
                myclass = " class='lightgrey'"; //当该日期在今天之前时，以浅灰色字体显示
            }else if (i==my_day && my_year==my_date.getFullYear() && my_month==my_date.getMonth()){
                myclass = " class='green greenbox'"; //当该日期是当天时，以绿色背景突出显示
            }else{
                myclass = " class='darkgrey'"; //当该日期在今后之后时，以深灰字体显示
            }
            str += "<li"+myclass+"  ondblclick='showMenu()' onclick='showRecodrs(this,"+i+")'>"+i+"</li>"; //创建日期节点
        }
        holder.innerHTML = str; //设置日期显示
        ctitle.innerHTML = month_name[my_month]; //设置英文月份显示
        cyear.innerHTML = my_year; //设置年份显示
    }
    //获取某年某月第一天是星期几
    function dayStart(month, year) {
        var tmpDate = new Date(year, month, 1);
        return (tmpDate.getDay());
    }

    //计算某年是不是闰年，通过求年份除以4的余数即可
    function daysMonth(month, year) {
        var tmp = year % 4;
        if (tmp == 0) {
            return (month_olympic[month]);
        } else {
            return (month_normal[month]);
        }
    }
    refreshDate();
</script>
</body>
</html>
