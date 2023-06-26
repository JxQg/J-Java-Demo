```json
{
  //服务器地址,非必须。导出postman建议设置成http://{{server}}方便直接在postman直接设置环境变量
  "serverUrl": "http://127.0.0.1",
  //导出postman时系统的公共url全局变量设置 @since 2.4.8,解决导出postman需要修改serverUrl问题
  "serverEnv": "http://{{server}}",
  //设置path前缀,非必须。如配置Servlet ContextPath 。@since 2.2.3
  "pathPrefix": "",
  //是否开启严格模式
  "isStrict": false,
  //是否将文档合并到一个文件中，一般推荐为true
  "allInOne": true,
  //指定文档的输出路径
  "outPath": "D://smart-doc",
  //是否覆盖旧的文件，主要用于mardown文件覆盖
  "coverOld": true,
  //@since 2.0.0 创建一个类似swagger的可调试接口的文档页面，仅在AllInOne模式中起作用。
  "createDebugPage": true,
  //controller包过滤，多个包用英文逗号隔开，2.2.2开始需要采用正则：com.test.controller.*
  "packageFilters": "",
  //只有每个controller生成一个html文件是才使用
  "md5EncryptedHtmlName": false,
  //基于highlight.js的代码高设置,可选值很多可查看码云wiki，喜欢配色统一简洁的同学可以不设置
  "style": "xt256",
  //配置自己的项目名称，不设置则插件自动获取pom中的projectName
  "projectName": "J-Java-Demo",
  //smart-doc默认支持spring和dubbo框架的文档，使用默认框架不用配置，当前支持spring、dubbo、JAX-RS、solon
  "framework": "spring",
  //目前未实现
  "skipTransientField": true,
  //接口标题排序，默认为false,@since 1.8.7版本开始
  "sortByTitle": false,
  //是否显示接口作者名称，默认是true,不想显示可关闭
  "showAuthor": true,
  //自动将驼峰入参字段在文档中转为下划线格式,//@since 1.8.7版本开始
  "requestFieldToUnderline": true,
  //自动将驼峰入参字段在文档中转为下划线格式,//@since 1.8.7版本开始
  "responseFieldToUnderline": true,
  //设置为true会将枚举详情展示到参数表中，默认关闭，//@since 1.8.8版本开始
  "inlineEnum": true,
  //设置允许递归执行的次数用于避免一些对象解析卡主，默认是7，正常为3次以内，//@since 1.8.8版本开始
  "recursionLimit": 7,
  //自定义设置输出文档名称, @since 1.9.0
  "allInOneDocFileName": "index.html",
  //是否将请求示例展示在文档中，默认true，@since 1.9.0
  "requestExample": "true",
  //是否将响应示例展示在文档中，默认为true，@since 1.9.0
  "responseExample": "true",
  //@since 2.2.5,是否在文档中显示请求参数表，默认值为 true。
  "requestParamsTable": true,
  //@since 2.2.5,是否在文档中显示返回参数表，默认值为 true。
  "responseParamsTable": true,
  //支持SpringMVC旧项目的url后缀,@since 2.1.0
  "urlSuffix": ".do",
  //配置true会在注释栏自动显示泛型的真实类型短类名，
  "displayActualType": false,
  //torna平台appToken,@since 2.0.9
  "appToken": "c16931fa6590483fb7a4e85340fcbfef",
  //torna 是否覆盖历史推送 @since 2.2.4
  "isReplace": true,
  //torna平台地址，填写自己的私有化部署地址@since 2.0.9
  "openUrl": "http://localhost:7700/api",
  //torna环境名称
  "debugEnvName": "测试环境",
  //推送torna配置接口服务地址
  "debugEnvUrl": "http://127.0.0.1",
  //启用会推送日志
  "tornaDebug": false,
  //忽略请求参数对象，把不想生成文档的参数对象屏蔽掉，@since 1.9.2
  "ignoreRequestParams": [
    "org.springframework.ui.ModelMap"
  ],
  //配置数据字典，没有需求可以不设置
  "dataDictionaries": [
    {
      //数据字典的名称
      "title": "http状态码字典",
      //数据字典枚举类名称
      "enumClassName": "com.power.common.enums.HttpCodeEnum",
      //数据字典字典码对应的字段名称
      "codeField": "code",
      //数据字典对象的描述信息字典,
      "descField": "message"
    },
    // @since 2.4.6开始可以配置枚举实现的接口， 当配置接口时title将使用实现枚举的类描述
    // 如果有已经实现的枚举需要忽略的话，可以在实现枚举类上增加@ignore进行忽略,
    {
      "enumClassName": "com.xx.IEnum",
      //数据字典字典码对应的字段名称
      "codeField": "code",
      //数据字典对象的描述信息字典
      "descField": "message"
    }
  ],
  //错误码列表，没有需求可以不设置
  "errorCodeDictionaries": [
    {
      "title": "title",
      //错误码枚举类
      "enumClassName": "com.power.common.enums.HttpCodeEnum",
      //错误码的code码字段名称
      "codeField": "code",
      //错误码的描述信息对应的字段名
      "descField": "message"
    },
    // @since 2.4.6开始可以配置枚举实现的接口， 当配置接口时title将使用实现枚举的类描述
    // 如果有已经实现的枚举需要忽略的话，可以在实现枚举类上增加@ignore进行忽略,
    {
      "enumClassName": "com.xx.IEnum",
      //数据字典字典码对应的字段名称,
      "codeField": "code",
      //数据字典对象的描述信息字典,
      "descField": "message"
    }
  ],
  //文档变更记录，非必须
  "revisionLogs": [
    {
      //文档版本号
      "version": "1.0",
      //文档修订时间
      "revisionTime": "2020-12-31 10:30",
      //变更操作状态，一般为：创建、更新等
      "status": "update",
      //文档变更作者
      "author": "author",
      //变更描述
      "remarks": "desc"
    }
  ],
  //自定义添加字段和注释，一般用户处理第三方jar包库，非必须,
  "customResponseFields": [
    {
      //覆盖响应码字段,
      "name": "code",
      //覆盖响应码的字段注释,
      "desc": "响应代码",
      //指定你要添加注释的类名,
      "ownerClassName": "org.springframework.data.domain.Pageable",
      //设置true会被自动忽略掉不会出现在文档中,
      "ignore": true,
      //设置响应码的值,
      "value": "00000"
    }
  ],
  //自定义请求体的注释，@since 2.1.3，非必须,
  "customRequestFields": [
    {
      //属性名,
      "name": "code",
      //描述,
      "desc": "状态码",
      //属性对应的类全路径,
      "ownerClassName": "com.xxx.constant.entity.Result",
      //默认值或者mock值,
      "value": "200",
      //是否必填,
      "required": true,
      //是否忽略,
      "ignore": false
    }
  ],
  //设置请求头，没有需求可以不设置,
  "requestHeaders": [
    {
      //请求头名称,
      "name": "token",
      //请求头类型,
      "type": "string",
      //请求头描述信息,
      "desc": "desc",
      //不设置默认null,
      "value": "token请求头的值",
      //是否必须,
      "required": false,
      //什么版本添加的改请求头,
      "since": "-",
      //请看https://smart-doc-group.github.io/#/zh-cn/diy/advancedFeatures?id=公共请求头,
      "pathPatterns": "/app/test/**",
      //请看https://smart-doc-group.github.io/#/zh-cn/diy/advancedFeatures?id=公共请求头,
      "excludePathPatterns": "/app/page/**"
    },
    {
      //请求头,
      "name": "appkey",
      //请求头类型,
      "type": "string",
      //请求头描述信息,
      "desc": "desc",
      //不设置默认null,
      "value": "appkey请求头的值",
      //是否必须,
      "required": false,
      //正则表达式过滤请求头,url匹配上才会添加该请求头，多个正则用分号隔开,
      "pathPatterns": "/test/add,/testConstants/1.0",
      //什么版本添加的改请求头,
      "since": "-"
    }
  ],
  //公共请求参数(通过拦截器处理的场景)，@since 2.2.3,没有需求请不要设置,
  "requestParams": [
    {
      //请求参数名称,
      "name": "configPathParam",
      //请求参数类型,
      "type": "string",
      //请求参数描述信息,
      "desc": "desc",
      "paramIn": "path",
      //不设置默认null,
      "value": "testPath",
      //是否必须,
      "required": false,
      //什么版本添加的改请求参数,
      "since": "-",
      //正则表达式过滤请求参数,
      "pathPatterns": "**",
      //参考请求头中的用法,
      "excludePathPatterns": "/app/page/**"
    },
    {
      //请求参数名称,
      "name": "configQueryParam",
      //请求参数类型,
      "type": "string",
      //请求参数描述信息,
      "desc": "desc",
      "paramIn": "query",
      //不设置默认null,
      "value": "testQuery",
      //是否必须,
      "required": false,
      //什么版本添加的改请求参数,
      "since": "-",
      //正则表达式过滤请求参数,
      "pathPatterns": "**",
      "excludePathPatterns": "/app/page/**"
    }
  ],
  "rpcApiDependencies": [
    {
      // 项目开放的dubbo api接口模块依赖，配置后输出到文档方便使用者集成
      "artifactId": "SpringBoot2-Dubbo-Api",
      "groupId": "com.demo",
      "version": "1.0.0"
    }
  ],
  "rpcConsumerConfig": "src/main/resources/consumer-example.conf",
  //文档中添加dubbo consumer集成配置，用于方便集成方可以快速集成
  "apiObjectReplacements": [
    {
      // 自smart-doc 1.8.5开始你可以使用自定义类覆盖其他类做文档渲染，非必须
      "className": "org.springframework.data.domain.Pageable",
      //自定义的PageRequestDto替换Pageable做文档渲染,
      "replacementClassName": "com.power.doc.model.PageRequestDto"
    }
  ],
  "apiConstants": [
    {
      //smart-doc在解析到常量时自动替换为具体的值，非必须，2.4.2开始不用配置，smart-doc支持自动解析常用
      "constantsClassName": "com.power.doc.constants.RequestParamConstant"
    }
  ],
  //自smart-doc 1.9.8起，非必须项，ResponseBodyAdvice统一返回设置(不要随便配置根据项目的技术来配置)，可用ignoreResponseBodyAdvice tag来忽略,
  "responseBodyAdvice": {
    //通用响应体,
    "className": "com.power.common.model.CommonResult"
  },
  ////自smart-doc 2.1.4 起，支持设置RequestBodyAdvice统一请求包装类，非必须,
  "requestBodyAdvice": {
    "className": "com.power.common.model.CommonResult"
  },
  // @since 2.2.5, 对不同的controller进行分组,
  "groups": [
    {
      "name": "测试分组",
      "apis": "com.power.doc.controller.app.*"
    }
  ]
}
```