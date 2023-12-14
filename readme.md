# PlantUML Plugin

基于 Spring Boot 及  PlantUML 官方的 jar 开发，部署在阿里云 Serverless 上

## 用法

向 `http://plantuml-plugin.fcv3.1451359068730290.cn-hangzhou.fc.devsapp.net/api/generate`  发送 `POST` 请求，参数是遵守 PlantUML 语法的描述，例如：

```json
{
  "content": "Bob -> Alice : 你好！"
}
```

服务端会返回生成的 UML 图，格式为 SVG

```json
{
  "image": "<?xml version=\"1.0\" encoding=\"us-ascii\" standalone=\"no\"?><svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" ..."
}
```

## License

MIT