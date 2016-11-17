<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 
<html>  
  <head>  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>午餐就餐登记</title>  
    <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="icon" type="image/x-icon" />
  </head>   
  <body>
<div data-role="page" id="pageone" data-theme="a">
    <link rel="stylesheet" href="http://libs.baidu.com/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css" />
    <script type="text/javascript" src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://libs.baidu.com/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/radialIndicator.js"></script>

    <script type="text/javascript">
        //页面加载完毕加载
        $(document).ready(function(){ 
        	createradial(100,36);	
        }); 
        
        function createradial(maxVal,value) {
        	$('#indicatorContainer').radialIndicator({
        		radius:140,//定义圆形指示器的内部的圆的半径。
                barColor: '#87CEEB',
                barWidth: 10,  //定义圆形指示器的刻度条的宽度。
                initValue: 0,  //圆形指示器初始化的值。
                roundCorner : true,//如果设置为true则圆形指示器的刻度bar有圆角
                percentage: false,
                format: '####人',
                maxValue:'${userNum}'   //总人数
            });
        	var radialObj = $('#indicatorContainer').data('radialIndicator');
        	//动态设置刻度
        	radialObj.animate('${dinnerNum}');
        	//绑定单击事件
        	$('#indicatorContainer').click(function(){
        		refurdinnerNum();    	
        	});
        	
        }
      //登记就餐人数：
        function insertDinnerInfo() {
        	$("#submitInput").attr("disabled","true");
        	var userId = $("#userId").val();
        	$.ajax({  
                data:"dinnerNum="+$("#dinnerNum").text()+"&userId="+userId,  
                type:"GET",  
                dataType: 'json',  
                url:"insertDinnerInfo",  
                error:function(data){  
                    alert("出错了！！:"+data.msg);  
                },  
                success:function(data){  
                    var userNum = data.userNum;
                    var dinnerNum = data.dinnerNum;
                    $("#userNum").text(userNum);
                    $("#dinnerNum").text(dinnerNum);
                    var radialObj = $('#indicatorContainer').data('radialIndicator');
                	//动态设置刻度
                	radialObj.option('maxValue',userNum); 
                    radialObj.option('initValue',0);    
                	radialObj.animate(dinnerNum);
                }  
                });  
        }
        
        //刷新就餐人数：
        function refurdinnerNum() {
        	$.ajax({  
                data:"dinnerNum="+$("#dinnerNum").text(),  
                type:"GET",  
                dataType: 'json',  
                url:"refDinnerInfo",  
                error:function(data){  
                    alert("出错了！！:"+data.msg);  
                },  
                success:function(data){  
                    var userNum = data.userNum;
                    var dinnerNum = data.dinnerNum;
                    $("#userNum").text(userNum);
                    $("#dinnerNum").text(dinnerNum);
                    var radialObj = $('#indicatorContainer').data('radialIndicator');
                	//动态设置刻度
                	radialObj.option('maxValue',userNum); 
                    radialObj.option('initValue',0);    
                	radialObj.animate(dinnerNum);
                }  
                });  
        }
    </script>
  <div data-role="header">
    <!--  <h1>午餐就餐登记</h1> -->
  </div>
  <div data-role="main" class="ui-content"  style="margin: 0 auto;text-align:center;">
    <label for="fname">${cutrdate}</label>
    <div id="indicatorContainer"></div>
    <div>
        <label for="fname">总人数：<span id="userNum">${userNum}</span>人  
                     就餐人数：<span id="dinnerNum">${dinnerNum}</span>人</label>
    </div>
    <span style="color:red;">（不就餐请勿点击）</span>
    <br>
    <form method="post" action=""> 
      <input type="hidden" id="userId" name="userId" value="${userId}"/>
      <input type="hidden" id="nickname" name="nickname" value="${nickname}"/>
      <c:if test="${isbuttAble eq '1'}">
          <button value="就餐登记" id="submitInput" onclick="insertDinnerInfo();return false;" >就餐登记</button>
      </c:if>
      <c:if test="${isbuttAble eq '0'}">
          <button value="就餐登记" id="submitInput" disabled=true>就餐登记</button>
      </c:if>
	</form>
  </div>
  <div data-role="footer" data-position="fixed">
      <!-- <h1>安徽省征信股份有限公司</h1> -->
  </div>
</div> 

  </body>  
</html>  