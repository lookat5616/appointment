package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class dinnerInfo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write(" \r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html> \r\n");
      out.write("<html>  \r\n");
      out.write("  <head>  \r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <title>午餐就餐登记</title>  \r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/img/favicon.ico\" rel=\"icon\" type=\"image/x-icon\" />\r\n");
      out.write("  </head>   \r\n");
      out.write("  <body>\r\n");
      out.write("<div data-role=\"page\" id=\"pageone\" data-theme=\"a\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"http://libs.baidu.com/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css\" />\r\n");
      out.write("    <script type=\"text/javascript\" src=\"http://libs.baidu.com/jquery/1.11.1/jquery.min.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"http://libs.baidu.com/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/radialIndicator.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        //页面加载完毕加载\r\n");
      out.write("        $(document).ready(function(){ \r\n");
      out.write("        \tcreateradial(100,36);\t\r\n");
      out.write("        }); \r\n");
      out.write("        \r\n");
      out.write("        function createradial(maxVal,value) {\r\n");
      out.write("        \t$('#indicatorContainer').radialIndicator({\r\n");
      out.write("        \t\tradius:140,//定义圆形指示器的内部的圆的半径。\r\n");
      out.write("                barColor: '#87CEEB',\r\n");
      out.write("                barWidth: 10,  //定义圆形指示器的刻度条的宽度。\r\n");
      out.write("                initValue: 0,  //圆形指示器初始化的值。\r\n");
      out.write("                roundCorner : true,//如果设置为true则圆形指示器的刻度bar有圆角\r\n");
      out.write("                percentage: false,\r\n");
      out.write("                format: '####人',\r\n");
      out.write("                maxValue:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userNum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("'   //总人数\r\n");
      out.write("            });\r\n");
      out.write("        \tvar radialObj = $('#indicatorContainer').data('radialIndicator');\r\n");
      out.write("        \t//动态设置刻度\r\n");
      out.write("        \tradialObj.animate('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dinnerNum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("');\r\n");
      out.write("        \t//绑定单击事件\r\n");
      out.write("        \t$('#indicatorContainer').click(function(){\r\n");
      out.write("        \t\trefurdinnerNum();    \t\r\n");
      out.write("        \t});\r\n");
      out.write("        \t\r\n");
      out.write("        }\r\n");
      out.write("      //登记就餐人数：\r\n");
      out.write("        function insertDinnerInfo() {\r\n");
      out.write("        \t$(\"#submitInput\").attr(\"disabled\",\"true\");\r\n");
      out.write("        \tvar userId = $(\"#userId\").val();\r\n");
      out.write("        \t$.ajax({  \r\n");
      out.write("                data:\"dinnerNum=\"+$(\"#dinnerNum\").text()+\"&userId=\"+userId,  \r\n");
      out.write("                type:\"GET\",  \r\n");
      out.write("                dataType: 'json',  \r\n");
      out.write("                url:\"insertDinnerInfo\",  \r\n");
      out.write("                error:function(data){  \r\n");
      out.write("                    alert(\"出错了！！:\"+data.msg);  \r\n");
      out.write("                },  \r\n");
      out.write("                success:function(data){  \r\n");
      out.write("                    var userNum = data.userNum;\r\n");
      out.write("                    var dinnerNum = data.dinnerNum;\r\n");
      out.write("                    $(\"#userNum\").text(userNum);\r\n");
      out.write("                    $(\"#dinnerNum\").text(dinnerNum);\r\n");
      out.write("                    var radialObj = $('#indicatorContainer').data('radialIndicator');\r\n");
      out.write("                \t//动态设置刻度\r\n");
      out.write("                \tradialObj.option('maxValue',userNum); \r\n");
      out.write("                    radialObj.option('initValue',0);    \r\n");
      out.write("                \tradialObj.animate(dinnerNum);\r\n");
      out.write("                }  \r\n");
      out.write("                });  \r\n");
      out.write("        }\r\n");
      out.write("        \r\n");
      out.write("        //刷新就餐人数：\r\n");
      out.write("        function refurdinnerNum() {\r\n");
      out.write("        \t$.ajax({  \r\n");
      out.write("                data:\"dinnerNum=\"+$(\"#dinnerNum\").text(),  \r\n");
      out.write("                type:\"GET\",  \r\n");
      out.write("                dataType: 'json',  \r\n");
      out.write("                url:\"refDinnerInfo\",  \r\n");
      out.write("                error:function(data){  \r\n");
      out.write("                    alert(\"出错了！！:\"+data.msg);  \r\n");
      out.write("                },  \r\n");
      out.write("                success:function(data){  \r\n");
      out.write("                    var userNum = data.userNum;\r\n");
      out.write("                    var dinnerNum = data.dinnerNum;\r\n");
      out.write("                    $(\"#userNum\").text(userNum);\r\n");
      out.write("                    $(\"#dinnerNum\").text(dinnerNum);\r\n");
      out.write("                    var radialObj = $('#indicatorContainer').data('radialIndicator');\r\n");
      out.write("                \t//动态设置刻度\r\n");
      out.write("                \tradialObj.option('maxValue',userNum); \r\n");
      out.write("                    radialObj.option('initValue',0);    \r\n");
      out.write("                \tradialObj.animate(dinnerNum);\r\n");
      out.write("                }  \r\n");
      out.write("                });  \r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("  <div data-role=\"header\">\r\n");
      out.write("    <!--  <h1>午餐就餐登记</h1> -->\r\n");
      out.write("  </div>\r\n");
      out.write("  <div data-role=\"main\" class=\"ui-content\"  style=\"margin: 0 auto;text-align:center;\">\r\n");
      out.write("    <label for=\"fname\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cutrdate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</label>\r\n");
      out.write("    <div id=\"indicatorContainer\"></div>\r\n");
      out.write("    <div>\r\n");
      out.write("        <label for=\"fname\">总人数：<span id=\"userNum\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userNum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</span>人  \r\n");
      out.write("                     就餐人数：<span id=\"dinnerNum\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dinnerNum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</span>人</label>\r\n");
      out.write("    </div>\r\n");
      out.write("    <span style=\"color:red;\">（不就餐请勿点击）</span>\r\n");
      out.write("    <br>\r\n");
      out.write("    <form method=\"post\" action=\"\"> \r\n");
      out.write("      <input type=\"hidden\" id=\"userId\" name=\"userId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("      <input type=\"hidden\" id=\"nickname\" name=\"nickname\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${nickname}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("      ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("      ");
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t</form>\r\n");
      out.write("  </div>\r\n");
      out.write("  <div data-role=\"footer\" data-position=\"fixed\">\r\n");
      out.write("      <!-- <h1>安徽省征信股份有限公司</h1> -->\r\n");
      out.write("  </div>\r\n");
      out.write("</div> \r\n");
      out.write("\r\n");
      out.write("  </body>  \r\n");
      out.write("</html>  ");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/jsp/dinnerInfo.jsp(108,6) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isbuttAble eq '1'}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("          <button value=\"就餐登记\" id=\"submitInput\" onclick=\"insertDinnerInfo();return false;\" >就餐登记</button>\r\n");
        out.write("      ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /WEB-INF/jsp/dinnerInfo.jsp(111,6) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isbuttAble eq '0'}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("          <button value=\"就餐登记\" id=\"submitInput\" disabled=true>就餐登记</button>\r\n");
        out.write("      ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }
}
