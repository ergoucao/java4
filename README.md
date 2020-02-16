# java4

## 作业提交系统
仅仅后端源码！！
	功能需求
前台：
作业提交（利用学号，姓名，考核）
后台：
	管理员登录
			考核删除
			考核发布
			考核查看（修改）
			考核下载
			进行标记

代码规范
			命名规范：
				包名：com.my.*;
				类名：首字母大写，驼峰命名；
				变量名和方法名：驼峰命名；
			注释规范：
方法功能注释。
方法体核心位置必须有说明注释。


## API:
登录页面：
Url:method?adname=用户名&adpwd=密码&ck=1需要记住密码0不需要&oper=“login”
return {error:0, time:2020-2-9}

提交页面:
Url:method?sId=学号&sname=姓名&test=考核码&oper=“submit” 
响应  {error:0,details:”文件上传详情”} 
注：error=1失败；

作业上传页面：
Url: upload?sId=学号&sname=姓名&checkCode=考核码

test考核发布页面：
Url: manager?tName=考核题目&tContent=考核内容&test=考核码&time=考核时限&oper=”giveTestNotice”
响应 {error:1(成功，0成功) }；

第一次进入作业详情页面：
Url:manager?page=1&CAPages=1&oper=”getWorkDetails”
响应: 
[{"CAPages":总页数},{"studentId":"学号","wid":"作业id","test":"考核码","studentName":"学生姓名","subTime":"作业提交时间"},{同理},{同理}]

获取作业信息：
Url:manager?test=”考核码”&page=页数&CAPages=0&&oper=”getWorkDetails”
响应[{"studentId":"学号","wid":"作业id","test":"考核码","studentName":"学生姓名","subTime":"作业提交时间"},{同理},{同理}]
pass不存在是即pass为空表示未审核，pass=1通过。

改变考核作业通过信息:
Url:manager?pass=通过是否或审核中&wid=作业id&oper=”changeWorkPass”
响应：{"error":"是否修改成功"}

删除作业：
Url:manager?wid=作业id&oper=”deleteWork”
响应：{"error":"是否修改成功"}

第一次进入考核详情页面：
Url:manager?page=1&CAPages=1&oper=”getTestDetails”;
[{"CAPages":总页码},{"period":"时间间隔","test":"考核码","tName":"考核题目","state":"状态","tContent":"考核内容","tDeadline":"考核日期","tId":"考核id"},{}]

获取考核信息：
Url:manager?page=页面&CAPages=0&oper=”getTestDetails”;
changeTestNotice”[{"CAPages":总页码},{"period":"时间间隔","test":"考核码","tName":"考核题目","state":"状态","tContent":"考核内容","tDeadline":"考核日期","tId":"考核id"},{}]

进入后台获取登录者信息。
Url:manager?oper=”useSession”


考核修改页面。
Url:manager?test=考核码&tName=考核题目&tContent=考核内容&period=考核时限&oper=”changeTestNotice””
”响应：{"error":"是否修改成功"}

考核状态修改页面。
Url:manager?test=考核码&state=考核状态&oper=”changeTestNoticeState””
”响应：{"error":"是否修改成功"}


考核删除页面。
Url:manager?test=考核码&oper=“deleteTestNotice”
响应：{"error":"是否修改成功"}

通过test查看考核信息
Url:manager?test=考核码&oper=”getTestNoticeByTest”
响应：{"period":"时间间隔","test":"考核码","tName":"考核题目","state":"状态","tContent":"考核内容","tDeadline":"考核日期","tId":"考核id"}

## 简单的安全：
1使用参数化查询 （Parameterized Query或Parameterized Statement）
，防范sql注入攻击
2.通过过滤器,正则表达式过滤特殊字符防止XSS攻击。
