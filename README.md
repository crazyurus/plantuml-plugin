# PlantUML Plugin

基于 Spring Boot 及 PlantUML 官方的 `net.sourceforge.plantuml` 包开发，部署在阿里云 Serverless 上

## 用法

向 `/api/generate` 发送 `POST` 请求，参数是遵守 PlantUML 语法的描述，例如：

```json
{
  "content": "Bob -> Alice : 你好！"
}
```

服务端会返回生成的 UML 图的 URL：

```json
{
  "url": "https://plantuml-plugin-image.oss-cn-hangzhou.aliyuncs.com/cd34de7eaf8d88f72b51756e59c90dc3.svg"
}
```

## License

[GPL-3.0](./LICENSE)