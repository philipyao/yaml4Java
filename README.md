---
title: "Hugo搭建博客quickstart"
date: 2018-05-15T10:49:04+08:00
draft: true
---

## 总揽

本文将介绍利用`Hugo`来快速搭建一个简单的个人博客。相对于其他静态网站生成器来说，Hugo 具备如下特点：

> 极快的页面编译生成速度。（ ~1 ms 每页面）  
> 完全跨平台支持，可以运行在  Mac OS X,  Linux,  Windows, 以及更多!   
> 安装方便 Installation  
> 本地调试 Usage 时通过 LiveReload 自动即时刷新页面   
> 完全的皮肤支持   
> 可以部署在任何的支持 HTTP 的服务器上    

在本文你将看到以下内容：

* 本地搭建: 先搭建一个博客，可以本地预览
* 远程部署：将博客部署到`github`上，对外开放浏览

## 本地搭建

1. 首先利用 hugo 命令行新建一个 website 目录（比如`blog`），用来放置博客文件。

```Bash
hugo new site blog
```

2. 为博客添加一个主题，主题会放置在 blog/themes 目录下。你可以在 [themes.gohugo.io](https://themes.gohugo.io/) 上查找你喜欢的主题，这些主题一般托管在 github 上，我们可以把它下载到 themes 目录下。  

这里我们选择一个叫 beautifulhugo 的主题：

```Bash
cd themes
git clone https://github.com/halogenica/beautifulhugo.git beautifulhugo
```

然后设置默认主题为 beautifulhugo ：  

```Bash
cd ..
vim config.toml
```

添加以下内容到 config.toml:

```
theme = "beautifulhugo"
```

3. 利用 hugo 命令行在 content 目录中添加一篇博客文章，文章都是用`md`格式来编辑：  

```Bash
hugo new post/first.md
```

修改 post/first.md 的内容如下：

```
### Hello Hugo

1. aaa
2. bbb
3. ccc
```

4. 利用 hugo 命令构建带 content 的博客，并在本地`localhost:1313`上启动 server：

```Bash
hugo server -D
```

5. 浏览器打开`http://localhost:1313/`可以看到博客内容。  

至此本地搭建完成。




## 部署博客到github
github页面 (`github pages`) 分两种：

* `User/Organization Pages` (https://<USERNAME|ORGANIZATION>.github.io/)
* `Project Pages`(https://<USERNAME|ORGANIZATION>.github.io/<PROJECT>/)

这里我们只介绍 `User/Organization` 类型的页面，也就是将博客页面托管到以下地址：

`https://<USERNAME|ORGANIZATION>.github.io/`

以下为步骤：

1. Ctrl+C 停掉本地 server

2. 在 github 上新建一个 repository（例如`blog`） 用来作为原始博客文件的远程仓库

3. 提交 blog 原始文件到 github :

```Bash
cd blog
git init
git add -f .
git commit -m "first commit"
git remote add origin git@github.com:<USERNAME>/blog.git
git push -u origin master
```

后续如果博客有更新，再有修改需要提交，只需要执行：  

```Bash
git add -A
git commit -m "your commit message"  
git push origin master  
```

4. 再新建一个repository `<USERNAME>.github.io` 用来作为渲染后的博客文件的远程仓库

5. 将`<USERNAME>.github.io`作为 submodule 关联到 public 目录：

```Bash
git submodule add -b master git@github.com:<USERNAME>/<USERNAME>.github.io.git public
```

6. 利用 hugo命令行 build 生成渲染后的博客文件，文件的默认存放路径就是`public` 

```Bash
hugo -D
```

7. 提交`public`目录的渲染文件到`github`:

```Bash
cd public
git add .
git commit -m "your commit message"
git push
```

8. 浏览器打开`https://<USERNAME>.github.io/`可以看到博客内容。

至此博客 github 部署完成。
