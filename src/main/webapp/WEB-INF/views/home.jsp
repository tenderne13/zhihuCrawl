<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
    String path = request.getContextPath();
    String basePath = request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; Charset=gb2312">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <title>微博</title>

    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css"/>
    <script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>
    <!--font-awesome-->
    <link href="${ctx}/static/plug/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <!--全局样式表-->
    <link href="${ctx}/static/css/global.css" rel="stylesheet" />
    <!-- 本页样式表 -->
    <link href="${ctx}/static/css/home.css" rel="stylesheet"/>
</head>
<body>
    <script type="text/javascript">
        layui.use(['layer'],function(){
            window.layer=layer;
        })

        $(function(){
            getUserData();
        });

        function getUserData(){
            var userId="${sessionScope.userId}";
            if(userId==null || userId==""){
                window.location.href="${ctx}/login";
            }
            $.post(
                "${ctx}/userData",function (data) {
                    data=JSON.parse(data);
                    console.log(data);
                    var userData=data[0].card_group[0].user;
                    var userIcon=userData.profile_image_url;
                    var userName=userData.name;
                    var description=userData.description;
                    var location =userData.nativePlace;
                    $("#userIcon").attr("src",userIcon);
                    $("#userName").html(userName);
                    $("#description").html(description);
                    $("#location").html(location);
                    layer.msg("加载完成");
                }

            );
        }



    </script>
    <!-- 导航 -->
    <nav class="blog-nav layui-header">
        <div class="blog-container">
            <a class="blog-logo" href="javaScript:;" id="title">新浪微博</a>
            <!-- 导航菜单 -->
            <ul class="layui-nav" lay-filter="nav">
                <li class="layui-nav-item layui-this">
                    <a href="home.html"><i class="fa fa-home fa-fw"></i>&nbsp;首页</a>
                </li>
                <li class="layui-nav-item">
                    <a href="article.html"><i class="fa fa-file-text fa-fw"></i>&nbsp;消息</a>
                </li>
                <li class="layui-nav-item">
                    <a href="resource.html"><i class="fa fa-tags fa-fw"></i>&nbsp;发现</a>
                </li>
                <li class="layui-nav-item">
                    <a href="timeline.html"><i class="fa fa-hourglass-half fa-fw"></i>&nbsp;我</a>
                </li>

            </ul>
            <!-- 手机和平板的导航开关 -->
            <a class="blog-navicon" href="javascript:;">
                <i class="fa fa-navicon"></i>
            </a>
        </div>
    </nav>
    <!-- 主体（一般只改变这里的内容） -->
    <div class="blog-body">
        <!-- canvas -->
        <canvas id="canvas-banner" style="background: #393D49;"></canvas>
        <!--为了及时效果需要立即设置canvas宽高，否则就在home.js中设置-->
        <script type="text/javascript">
            var canvas = document.getElementById('canvas-banner');
            canvas.width = window.document.body.clientWidth - 10;//减去滚动条的宽度
            if (screen.width >= 992) {
                canvas.height = window.innerHeight * 1 / 3;
            } else {
                canvas.height = window.innerHeight * 2 / 7;
            }
        </script>
        <!-- 这个一般才是真正的主体内容 -->
        <div class="blog-container">
            <div class="blog-main">

                <!--左边文章列表-->
                <div class="blog-main-left">
                    <div class="article shadow">
                        <div class="article-left">
                            <img data-node="pic" data-act-type="hover" src="http://wx2.sinaimg.cn/thumb180/005Jz1hHly1fi819jukiij30a9cmie83.jpg" class="loaded">
                        </div>
                        <div class="article-right">
                            <div class="article-title">
                                <a class='k' href='https://m.weibo.cn/k/%E5%85%B3%E6%B3%A8%E8%B5%B5%E7%9F%B3%E7%9A%84%E6%95%85%E4%BA%8B?from=feed'>#关注赵石的故事#</a><span class="iconimg iconimg-xs"><img src="//h5.sinaimg.cn/m/emoticon/icon/others/o_huatong-9f86617336.png" style="width:1em;height:1em;" alt="[话筒]"></span> ：《赵石的故事》第一千零三十六集———不灭的印记；睡个懒觉也能出事....................<span class="iconimg iconimg-xs"><img src="//h5.sinaimg.cn/m/emoticon/icon/others/d_erha-0d2bea3a7d.png" style="width:1em;height:1em;" alt="[二哈]"></span>
                            </div>
                            <div class="article-abstract">

                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="article-footer">
                            <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;2017-03-18</span>
                            <span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;Absolutely</span>
                            <span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="#">Web前端</a></span>
                            <span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;0</span>
                            <span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;4</span>
                        </div>
                    </div>
                    <div class="article shadow">
                        <div class="article-left">
                            <img src="../images/cover/201703181909057125.jpg" alt="基于laypage的layui扩展模块（pagesize.js）！" />
                        </div>
                        <div class="article-right">
                            <div class="article-title">
                                <a href="detail.html">基于laypage的layui扩展模块（pagesize.js）！</a>
                            </div>
                            <div class="article-abstract">
                                该模块主要是针对当前版本laypage没有页容量控制功能而制作，使用该模块后即可实现每页显示多少条数据的控制！本人原创，但是可能有可能只对本人的分页写法有用！
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="article-footer">
                            <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;2017-03-18</span>
                            <span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;Absolutely</span>
                            <span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="#">Web前端</a></span>
                            <span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;0</span>
                            <span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;4</span>
                        </div>
                    </div>
                    <div class="article shadow">
                        <div class="article-left">
                            <img src="../images/cover/201703181909057125.jpg" alt="基于laypage的layui扩展模块（pagesize.js）！" />
                        </div>
                        <div class="article-right">
                            <div class="article-title">
                                <a href="detail.html">基于laypage的layui扩展模块（pagesize.js）！</a>
                            </div>
                            <div class="article-abstract">
                                该模块主要是针对当前版本laypage没有页容量控制功能而制作，使用该模块后即可实现每页显示多少条数据的控制！本人原创，但是可能有可能只对本人的分页写法有用！
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="article-footer">
                            <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;2017-03-18</span>
                            <span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;Absolutely</span>
                            <span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="#">Web前端</a></span>
                            <span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;0</span>
                            <span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;4</span>
                        </div>
                    </div>
                    <div class="article shadow">
                        <div class="article-left">
                            <img src="../images/cover/201703181909057125.jpg" alt="基于laypage的layui扩展模块（pagesize.js）！" />
                        </div>
                        <div class="article-right">
                            <div class="article-title">
                                <a href="detail.html">基于laypage的layui扩展模块（pagesize.js）！</a>
                            </div>
                            <div class="article-abstract">
                                该模块主要是针对当前版本laypage没有页容量控制功能而制作，使用该模块后即可实现每页显示多少条数据的控制！本人原创，但是可能有可能只对本人的分页写法有用！
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="article-footer">
                            <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;2017-03-18</span>
                            <span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;Absolutely</span>
                            <span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="#">Web前端</a></span>
                            <span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;0</span>
                            <span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;4</span>
                        </div>
                    </div>
                    <div class="article shadow">
                        <div class="article-left">
                            <img src="../images/cover/201703181909057125.jpg" alt="基于laypage的layui扩展模块（pagesize.js）！" />
                        </div>
                        <div class="article-right">
                            <div class="article-title">
                                <a href="detail.html">基于laypage的layui扩展模块（pagesize.js）！</a>
                            </div>
                            <div class="article-abstract">
                                该模块主要是针对当前版本laypage没有页容量控制功能而制作，使用该模块后即可实现每页显示多少条数据的控制！本人原创，但是可能有可能只对本人的分页写法有用！
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="article-footer">
                            <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;2017-03-18</span>
                            <span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;Absolutely</span>
                            <span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="#">Web前端</a></span>
                            <span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;0</span>
                            <span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;4</span>
                        </div>
                    </div>
                    <div class="article shadow">
                        <div class="article-left">
                            <img src="../images/cover/201703181909057125.jpg" alt="基于laypage的layui扩展模块（pagesize.js）！" />
                        </div>
                        <div class="article-right">
                            <div class="article-title">
                                <a href="detail.html">基于laypage的layui扩展模块（pagesize.js）！</a>
                            </div>
                            <div class="article-abstract">
                                该模块主要是针对当前版本laypage没有页容量控制功能而制作，使用该模块后即可实现每页显示多少条数据的控制！本人原创，但是可能有可能只对本人的分页写法有用！
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="article-footer">
                            <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;2017-03-18</span>
                            <span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;Absolutely</span>
                            <span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="#">Web前端</a></span>
                            <span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;0</span>
                            <span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;4</span>
                        </div>
                    </div>
                    <div class="article shadow">
                        <div class="article-left">
                            <img src="../images/cover/201703181909057125.jpg" alt="基于laypage的layui扩展模块（pagesize.js）！" />
                        </div>
                        <div class="article-right">
                            <div class="article-title">
                                <a href="detail.html">基于laypage的layui扩展模块（pagesize.js）！</a>
                            </div>
                            <div class="article-abstract">
                                该模块主要是针对当前版本laypage没有页容量控制功能而制作，使用该模块后即可实现每页显示多少条数据的控制！本人原创，但是可能有可能只对本人的分页写法有用！
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="article-footer">
                            <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;2017-03-18</span>
                            <span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;Absolutely</span>
                            <span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="#">Web前端</a></span>
                            <span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;0</span>
                            <span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;4</span>
                        </div>
                    </div>
                    <div class="article shadow">
                        <div class="article-left">
                            <img src="../images/cover/201703181909057125.jpg" alt="基于laypage的layui扩展模块（pagesize.js）！" />
                        </div>
                        <div class="article-right">
                            <div class="article-title">
                                <a href="detail.html">基于laypage的layui扩展模块（pagesize.js）！</a>
                            </div>
                            <div class="article-abstract">
                                该模块主要是针对当前版本laypage没有页容量控制功能而制作，使用该模块后即可实现每页显示多少条数据的控制！本人原创，但是可能有可能只对本人的分页写法有用！
                            </div>
                        </div>
                        <div class="clear"></div>
                        <div class="article-footer">
                            <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;2017-03-18</span>
                            <span class="article-author"><i class="fa fa-user"></i>&nbsp;&nbsp;Absolutely</span>
                            <span><i class="fa fa-tag"></i>&nbsp;&nbsp;<a href="#">Web前端</a></span>
                            <span class="article-viewinfo"><i class="fa fa-eye"></i>&nbsp;0</span>
                            <span class="article-viewinfo"><i class="fa fa-commenting"></i>&nbsp;4</span>
                        </div>
                    </div>
                </div>
                <!--右边小栏目-->
                <div class="blog-main-right">
                    <div class="blogerinfo shadow">
                        <div class="blogerinfo-figure">
                            <img src="" alt="Absolutely" id="userIcon" />
                        </div>
                        <p class="blogerinfo-nickname" id="userName"></p>
                        <p class="blogerinfo-introduce" id="description"></p>
                        <p class="blogerinfo-location"><i class="fa fa-location-arrow"></i>&nbsp;<font id="location"></font></p>
                        <hr />
                        <div class="blogerinfo-contact">
                            <a target="_blank" title="QQ交流" href="javascript:layer.msg('启动QQ会话窗口')"><i class="fa fa-qq fa-2x"></i></a>
                            <a target="_blank" title="给我写信" href="javascript:layer.msg('启动邮我窗口')"><i class="fa fa-envelope fa-2x"></i></a>
                            <a target="_blank" title="新浪微博" href="javascript:layer.msg('转到你的微博主页')"><i class="fa fa-weibo fa-2x"></i></a>
                            <a target="_blank" title="码云" href="javascript:layer.msg('转到你的github主页')"><i class="fa fa-git fa-2x"></i></a>
                        </div>
                    </div>
                    <div></div><!--占位-->
                    <%--<div class="blog-module shadow">
                        <div class="blog-module-title">热文排行</div>
                        <ul class="fa-ul blog-module-ul">
                            <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">Web安全之跨站请求伪造CSRF</a></li>
                            <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">ASP.NET MVC 防范跨站请求伪造（CSRF）</a></li>
                            <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">常用正则表达式</a></li>
                            <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">EF CodeFirst数据迁移常用指令</a></li>
                            <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">浅谈.NET Framework基元类型</a></li>
                            <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">C#基础知识回顾-扩展方法</a></li>
                            <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">一步步制作时光轴（一）（HTML篇）</a></li>
                            <li><i class="fa-li fa fa-hand-o-right"></i><a href="detail.html">一步步制作时光轴（二）（CSS篇）</a></li>
                        </ul>
                    </div>
                    <div class="blog-module shadow">
                        <div class="blog-module-title">最近分享</div>
                        <ul class="fa-ul blog-module-ul">
                            <li><i class="fa-li fa fa-hand-o-right"></i><a href="http://pan.baidu.com/s/1c1BJ6Qc" target="_blank">Canvas</a></li>
                            <li><i class="fa-li fa fa-hand-o-right"></i><a href="http://pan.baidu.com/s/1kVK8UhT" target="_blank">pagesize.js</a></li>
                            <li><i class="fa-li fa fa-hand-o-right"></i><a href="https://pan.baidu.com/s/1mit2aiW" target="_blank">时光轴</a></li>
                            <li><i class="fa-li fa fa-hand-o-right"></i><a href="https://pan.baidu.com/s/1nuAKF81" target="_blank">图片轮播</a></li>
                        </ul>
                    </div>
                    <div class="blog-module shadow">
                        <div class="blog-module-title">一路走来</div>
                        <dl class="footprint">
                            <dt>2017年03月12日</dt>
                            <dd>新增留言回复功能！人人都可参与回复！</dd>
                            <dt>2017年03月10日</dt>
                            <dd>不落阁2.0基本功能完成，正式上线！</dd>
                            <dt>2017年03月09日</dt>
                            <dd>新增文章搜索功能！</dd>
                            <dt>2017年02月25日</dt>
                            <dd>QQ互联接入网站，可QQ登陆发表评论与留言！</dd>
                        </dl>
                    </div>
                    <div class="blog-module shadow">
                        <div class="blog-module-title">后台记录</div>
                        <dl class="footprint">
                            <dt>2017年03月16日</dt>
                            <dd>分页新增页容量控制</dd>
                            <dt>2017年03月12日</dt>
                            <dd>新增管家提醒功能</dd>
                            <dt>2017年03月10日</dt>
                            <dd>新增Win10快捷菜单</dd>
                        </dl>
                    </div>
                    <div class="blog-module shadow">
                        <div class="blog-module-title">友情链接</div>
                        <ul class="blogroll">
                            <li><a target="_blank" href="http://www.layui.com/" title="Layui">Layui</a></li>
                            <li><a target="_blank" href="http://www.pagemark.cn/" title="页签">页签</a></li>
                        </ul>
                    </div>--%>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <!-- 底部 -->
    <footer class="blog-footer">
        <p><span>Copyright</span><span>&copy;</span><span>2017</span><a href="#">新浪微博</a><span>Design By tenderne丶</span></p>
    </footer>
    <!--侧边导航-->
    <ul class="layui-nav layui-nav-tree layui-nav-side blog-nav-left layui-hide" lay-filter="nav">
        <li class="layui-nav-item layui-this">
            <a href="home.html"><i class="fa fa-home fa-fw"></i>&nbsp;网站首页</a>
        </li>
        <li class="layui-nav-item">
            <a href="article.html"><i class="fa fa-file-text fa-fw"></i>&nbsp;消息</a>
        </li>
        <li class="layui-nav-item">
            <a href="resource.html"><i class="fa fa-tags fa-fw"></i>&nbsp;发现</a>
        </li>
        <li class="layui-nav-item">
            <a href="timeline.html"><i class="fa fa-road fa-fw"></i>&nbsp;我</a>
        </li>

    </ul>
    <!--分享窗体-->
    <div class="blog-share layui-hide">
        <div class="blog-share-body">
            <div style="width: 200px;height:100%;">
                <div class="bdsharebuttonbox">
                    <a class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
                    <a class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
                    <a class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
                    <a class="bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a>
                </div>
            </div>
        </div>
    </div>
    <!--遮罩-->
    <div class="blog-mask animated layui-hide"></div>
    <!-- layui.js -->
    <!-- 全局脚本 -->
    <script src="${ctx}/static/js/global.js"></script>
    <!-- 本页脚本 -->
    <script src="${ctx}/static/js/home.js"></script>
</body>
</html>