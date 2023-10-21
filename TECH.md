# TECH

### EC2 上传文件

```bash
scp -i .\project-KP.pem .\KaslanaRegressionBackEnd-1.0-SNAPSHOT.jar ubuntu@ec2-13-51-64-104.eu-north-1.compute.amazonaws.com:/home/ubuntu
```

### Spring 打包

clean-compile-package

### Vue 打包

npm run build

将dist中文件放入springboot resources static 文件夹中再打包

### tmux

```
tmux new　　创建默认名称的会话（在tmux命令模式使用new命令可实现同样的功能，其他命令同理，后文不再列出tmux终端命令）

tmux new -s mysession　　创建名为mysession的会话

tmux ls　　显示会话列表

tmux a　　连接上一个会话

tmux a -t mysession　　连接指定会话

tmux rename -t s1 s2　　重命名会话s1为s2

tmux kill-session　　关闭上次打开的会话

tmux kill-session -t s1　　关闭会话s1

tmux kill-session -a -t s1　　关闭除s1外的所有会话

tmux kill-server　　关闭所有会话附上原文出处链接及本声明。
```

