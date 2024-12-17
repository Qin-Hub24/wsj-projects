### Cases 档案

#### cases查询

- 接口说明

  1. 接口功能

     本接口动态查询获取档案

  2. 请求地址	http://localhost:9090/api/files/like_case

  3. 请求方式 GET

- 请求示例

  | 字段名     | 类型   | 是否必填 |
  | ---------- | ------ | -------- |
  | caseNumber | String | 否       |
  | title      | String | 否       |
  | status     | String | 否       |

  ```apl
  http://localhost:9090/api/files/like_case?caseNumber=10003&title=鼠&status=已完成
  # 注意不传参数就是查询所有
  ```
  
- 响应示例

  ```json
  
  {
      "code": 200,
      "msg": "success",
      "data": {
          "content": [
              {
                  "id": 1,
                  "caseNumber": "10001",
                  "title": "乌克兰烤乳猪",
                  "description": "烤乳猪是广东最著名的特色菜，属粤菜广府菜。",
                  "status": "未完成",
                  "createdAt": "2024-11-07T12:30:36",
                  "updatedAt": "2024-11-10T17:50:15"
              },
          ]
  }
  ```

#### cases 添加

- 接口说明

  1. 接口功能

     本接口添加档案

  2. 请求地址	http://localhost:9090/api/files/add_case

  3. 请求方式    POST

- 请求示例

  | 字段名      | 类型   | 是否必填 |
  | ----------- | ------ | -------- |
  | caseNumber  | String | 是       |
  | title       | String | 否       |
  | description | String | 否       |
  | status      | String | 否       |

  ```json
  {
      "caseNumber": "10007",
      "title": "李帅",
      "description": "烤乳猪是广东最著名的特色菜，属粤菜广府菜。",
      "status": "未完成",
   }
  ```

- 响应示例

  ```json
  // 成功示例
  {
      "code": 200,
      "msg": "success",
      "data": "添加成功"
  }
  // 失败示例
  {
      "code": 403,
      "msg": "error",
      "data": "案件编号重复:10007"
  }
  ```

#### cases 修改

  - 接口说明
  
    1. 接口功能
    
       本接口修改档案
    
    2. 请求地址	http://localhost:9090/api/files/updated_case
    
    3. 请求方式    PUT
  
  - 请求示例

  ```json
  {
      "caseNumber": "10009",
      "title": "怪兽",
      "description": "烤乳猪是广东最著名的特色菜，属粤菜广府菜，并且是“满汉全席”中的主打菜肴之一。",
      "status": "已完成",
  }
  ```

  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": [
            {
                "id": 12,
                "caseNumber": "10007",
                "title": "怪兽",
                "description": "烤乳猪是广东最著名的特色菜，属粤菜广府菜。",
                "status": "已完成",
                "createdAt": "2024-11-09T15:11:17",
                "updatedAt": "2024-11-14T13:59:27.6089618"
            }
        ]
    }
    // 失败示例
    {
        "code": 403,
        "msg": "error",
        "data": "修改失败,案件编号已存在:10009"
    }
    ```

#### cases 删除

  - 接口说明

    1. 接口功能

       本接口删除档案

    2. 请求地址	http://localhost:9090/api/files/delete_case

    3. 请求方式    DELETE

  - 请求示例

    ```apl
    http://localhost:9090/api/files/delete_case/10005
    ```

  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": "删除成功"
    }
    
    // 失败示例
    {
        "code": 403,
        "msg": "error",
        "data": "案件编号不存在:10002"
    }
    ```

### Case_files 文件

#### files查询

  - 接口说明

    1. 接口功能

       本接口可以动态查询文件

    2. 请求地址	http://localhost:9090/api/files/sh_caseFile

    3. 请求方式    GET

  - 请求示例

    | 字段名      | 类型    | 是否必填 |
    | ----------- | ------- | -------- |
    | name        | String  | 否       |
    | fileType    | String  | 否       |
    | version     | int     | 否       |
    | isCurrent   | boolean | 否       |
    | description | String  | 否       |
    | userId      | int     | 是       |

    ```apl
    http://localhost:9090/api/files/sh_caseFile?name=th&fileType=word&version=1&isCurrent=1&description=风景&userId=1
    # 传参就是查询全部
    ```

  - 响应示例

    ```json
    {
        "code": 200,
        "msg": "success",
        "data": {
            "content": [
                {
                    "id": 2,
                    "caseId": {
                        "id": 1,
                        "caseNumber": "10001",
                        "title": "李帅",
                        "description": "戴眼镜爱摄像的,老野。",
                        "status": "0",
                        "createdAt": "2024-11-21T19:59:47",
                        "updatedAt": "2024-11-21T19:59:47"
                    },
                    "name": "th_gerder.jpg",
                    "path": "imageText/20241121215616_th_gerder.jpg",
                    "size": 11315,
                    "fileType": "image/jpeg",
                    "uploadedBy": {
                        "id": 1,
                        "username": "lishuai",
                        "password": "$2a$10$68CGSOCaV3/q.qbdHprTQ.jHHpX.qhAdCJSijDqlz3BsJCV/ueAe6",
                        "email": "123467890@qq.com",
                        "createdAt": "2024-11-21T19:59:55",
                        "updatedAt": "2024-11-21T19:59:55",
                        "status": 1
                    },
                    "createdAt": "2024-11-21T21:55:02",
                    "updatedAt": "2024-11-21T21:56:17",
                    "description": "美女图片",
                    "version": 2,
                    "isCurrent": true
                }
            ],
            "pageable": {
                "sort": {
                    "empty": true,
                    "sorted": false,
                    "unsorted": true
                },
                "offset": 0,
                "pageNumber": 0,
                "pageSize": 10,
                "paged": true,
                "unpaged": false
            },
            "last": true,
            "totalElements": 2,
            "totalPages": 1,
            "size": 10,
            "number": 0,
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "first": true,
            "numberOfElements": 2,
            "empty": false
        }
    }
    ```

#### files 添加

  - 接口说明

    1. 接口功能

       本接口添加文件

    2. 请求地址	http://localhost:9090/api/files/addCaseFile

    3. 请求方式    POST

    4. 请求头       Content-Type: multipart/form-data

  - 请求示例

    | 字段名      | 类型   | 是否必填 |
    | ----------- | ------ | -------- |
    | file        | File   | 是       |
    | caseId      | int    | 是       |
    | uploadedBy  | int    | 是       |
    | description | String | 是       |
    
  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": "http://localhost:9090/imageText/20241121220639_th.jpg"
    }
    
    // 失败示例
    {
        "code": 403,
        "msg": "error",
        "data": "Failed to upload file"
    }
    ```

#### files 修改

  - 接口说明

    1. 接口功能

       本接口修改文件

    2. 请求地址	http://localhost:9090/api/files/up_caseFile

    3. 请求方式    PUT

    4. 请求头      Content-Type: multipart/form-data

  - 请求示例

    | 字段名      | 类型   | 是否必填 |
    | ----------- | ------ | -------- |
    | file        | File   | 是       |
    | caseId      | int    | 是       |
    | uploadedBy  | int    | 是       |
    | description | String | 是       |
    | fileId      | int    | 是       |
    
  - 响应示例

    ```json
    {
        "code": 200,
        "msg": "更新成功",
        "data": {
            "id": 2,
            "caseId": {
                "id": 1,
                "caseNumber": "10001",
                "title": "李帅",
                "description": "戴眼镜爱摄像的,老野。",
                "status": "0",
                "createdAt": "2024-11-21T19:59:47",
                "updatedAt": "2024-11-21T19:59:47"
            },
            "name": "th_gerder.jpg",
            "path": "imageText/20241121215616_th_gerder.jpg",
            "size": 11315,
            "fileType": "image/jpeg",
            "uploadedBy": {
                "id": 1,
                "username": "lishuai",
                "password": "$2a$10$68CGSOCaV3/q.qbdHprTQ.jHHpX.qhAdCJSijDqlz3BsJCV/ueAe6",
                "email": "123467890@qq.com",
                "createdAt": "2024-11-21T19:59:55",
                "updatedAt": "2024-11-21T19:59:55",
                "status": 1
            },
            "createdAt": "2024-11-21T21:55:02",
            "updatedAt": "2024-11-21T21:56:16.83073",
            "description": "美女图片",
            "version": 2,
            "isCurrent": true
        }
    }
    ```

#### files 删除

  - 接口说明

    1. 接口功能

       本接口删除文件

    2. 请求地址	http://localhost:9090/api/files/d_caseFile/3?userId=1

    3. 请求方式    DELETE

  - 请求示例

    | 字段名 | 类型 | 是否必填 |
    | ------ | ---- | -------- |
    | id     | int  | 是       |
    | userId | int  | 是       |
    
    
    
    ```apl
    http://localhost:9090/api/files/d_caseFile/3?userId=1
    http://localhost:9090/api/files/d_caseFile/{id}?userId=1
    ```

  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": "删除成功"
    }
    
    // 失败示例
    {
        "code": 500,
        "msg": "error",
        "data": "File not found: 4"
    }
    ```

### File_categories 文件分类

#### categories 查询

  - 接口说明

    1. 接口功能

       本接口条件查询分类

    2. 请求地址	http://localhost:9090/api/files/condition

    3. 请求方式    GET

  - 请求示例

    | 字段名      | 类型   | 是否必填 |
    | ----------- | ------ | -------- |
    | name        | String | 否       |
    | description | String | 否       |

    ```apl
    http://localhost:9090/api/files/condition?name=lanhaihu&description=一个
    # 不传参就是查询全部
    ```

  - 响应示例

    ```json
    {
        "code": 200,
        "msg": "success",
        "data": [
            {
                "id": 3,
                "name": "lanhaihu",
                "description": "这是一个文件",
                "createdAt": "2024-11-13T09:20:29",
                "updatedAt": "2024-11-13T09:20:29"
            }
        ]
    }
    ```

#### categories 添加

  - 接口说明

    1. 接口功能

       本接口添加分类

    2. 请求地址	http://localhost:9090/api/files/addCategories

    3. 请求方式    POST

  - 请求示例

    ```json
    {
        "name": "lishaui",
        "description": "lishuai的个人介绍，感情史，学历，性格等"
    }
    ```

  - 响应示例

    ```json
    {
        "code": 200,
        "msg": "success",
        "data": "添加成功"
    }
    ```

#### categories 修改

  - 接口说明

    1. 接口功能

       本接口删除修改分类

    2. 请求地址	http://localhost:9090/api/files/modifyCategories

    3. 请求方式    PUT

  - 请求示例

    ```json
    {
        "id": 1,
        "name": "laozhu",
        "description": "是个..."
    }
    ```

  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": [
            {
                "id": 6,
                "name": "laozhu",
                "description": "在干嘛",
                "createdAt": "2024-11-14T15:55:25",
                "updatedAt": "2024-11-14T15:57:48.8239349"
            }
        ]
    }
    
    // 失败示例
    {
        "code": 403,
        "msg": "error",
        "data": "修改失败该分类不存在"
    }
    ```

#### categories 删除

  - 接口说明

    1. 接口功能

       本接口删除分类

    2. 请求地址	http://localhost:9090/api/files/multiple_case

    3. 请求方式    DELETE

  - 请求示例

    | 字段名 | 类型 | 是否必填 |
    | ------ | ---- | -------- |
    | id     | int  | 是       |

    ```apl
    http://localhost:9090/api/files/5
    ```

  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": "删除成功"
    }
    
    // 失败示例
    {
        "code": 403,
        "msg": "error",
        "data": "该数据已经关联禁止删除"
    }
    {
        "code": 403,
        "msg": "error",
        "data": "删除失败1不存在"
    }
    ```

### File_category_relation 文件-分类关联

#### relation 查询

  - 接口说明

    1. 接口功能

       本接口查询文件-分类关联

    2. 请求地址	http://localhost:9090/api/files/queryR

    3. 请求方式    GET

  - 请求示例

  - 响应示例

    ```json
    {
        "code": 200,
        "msg": "success",
        "data": {
            "content": [
                {
                    "id": 8,
                    "fileId": {
                        "id": 18,
                        "caseId": {
                            "id": 11,
                            "caseNumber": "10005",
                            "title": "李帅",
                            "description": "烤乳猪是广东最著名的特色菜，属粤菜广府菜。",
                            "status": "未完成",
                            "createdAt": "2024-11-09T14:17:26",
                            "updatedAt": "2024-11-09T14:17:26"
                        },
                        "name": "老猪",
                        "path": "/uploads/example.word",
                        "size": 1024,
                        "fileType": "word",
                        "uploadedBy": {
                            "id": 1,
                            "username": "lishuai",
                            "password": "1234",
                            "email": "23156@qq.com",
                            "createdAt": "2024-11-13T13:46:57",
                            "updatedAt": "2024-11-13T13:47:02",
                            "status": 1
                        },
                        "createdAt": "2024-11-13T14:52:09",
                        "updatedAt": "2024-11-13T14:52:09",
                        "description": "这是一个文件",
                        "version": 1,
                        "isCurrent": true
                    },
                    "categoryId": {
                        "id": 3,
                        "name": "lanhaihu",
                        "description": "这是一个文件",
                        "createdAt": "2024-11-13T09:20:29",
                        "updatedAt": "2024-11-13T09:20:29"
                    },
                    "createdAt": "2024-11-13T21:53:45"
                }
            ]
    }
    ```

#### relation 添加

  - 接口说明

    1. 接口功能

       本接口添加文件-分类关联

    2. 请求地址	http://localhost:9090/api/files/multiple_case

    3. 请求方式    POST

  - 请求示例

    | 字段名        | 类型 | 是否必填 |
    | ------------- | ---- | -------- |
    | fileId.id     | int  | 是       |
    | categoryId.id | int  | 是       |

    ```json
    {
        "fileId": {
            "id": 22
        },
        "categoryId": {
            "id": 5
        }
    }
    ```

  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": "绑定成功"
    }
    // 失败示例
    {
        "code": 200,
        "msg": "success",
        "data": "该文件或文件夹已绑定"
    }
    ```

#### relation 删除

  - 接口说明

    1. 接口功能

       本接口删除文件-分类关联

    2. 请求地址	http://localhost:9090/api/files/unbind

    3. 请求方式    DELETE

  - 请求示例

    ```json
    {
        "fileId": {
            "id": 12
        },
        "categoryId": {
            "id": 5
        }
    }
    ```

  - 响应示例

    ```json
    {
        "code": 200,
        "msg": "success",
        "data": "解绑成功"
    }
    ```

### File_tags 文件标签

#### tags 查询

  - 接口说明

    1. 接口功能

       本接口动态查询接口

    2. 请求地址	http://localhost:9090/api/files/tagsDynamics

    3. 请求方式    GET

  - 请求示例

    | 字段名 | 类型   | 是否必填 |
    | ------ | ------ | -------- |
    | name   | String | 否       |

    ```apl
    http://localhost:9090/api/files/tagsDynamics?name=机密
    # 动态查询不传参就查询全部
    ```

  - 响应示例

    ```json
    {
        "code": 200,
        "msg": "success",
        "data": [
            {
                "id": 3,
                "name": "机密",
                "createdAt": "2024-11-14T09:42:43"
            }
        ]
    }
    ```

#### tags 添加

  - 接口说明

    1. 接口功能

       本接口添加标签

    2. 请求地址	http://localhost:9090/api/files/addTags

    3. 请求方式    POST

  - 请求示例

    ```json
    {
    	"name": 重要
    }
    ```

  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": "添加成功"
    }
    // 失败示例
    {
        "code": 403,
        "msg": "error",
        "data": "添加失败重要重复"
    }
    ```

#### tags 删除

  - 接口说明

    1. 接口功能

       本接口删除标签

    2. 请求地址	http://localhost:9090/api/files/deleteTags

    3. 请求方式    DELETE

  - 请求示例

    ```
    {
        "id": 4
    }
    ```

  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": "删除成功"
    }
    // 失败示例
    {
        "code": 403,
        "msg": "error",
        "data": "该数据已经关联禁止删除"
    }
    {
        "code": 403,
        "msg": "error",
        "data": "删除失败没有该id"
    }
    ```

### File_tag_relations 文件-标签关联

#### tag_relations 查询

  - 接口说明

    1. 接口功能

       本接口查询文件-标签关联

    2. 请求地址	http://localhost:9090/api/files/fileAndTag

    3. 请求方式    GET

  - 请求示例

  - 响应示例

    ```json
    {
        "code": 200,
        "msg": "success",
        "data": {
            "content": [
                {
                    "id": 3,
                    "fileId": {
                        "id": 18,
                        "caseId": {
                            "id": 11,
                            "caseNumber": "10005",
                            "title": "李帅",
                            "description": "烤乳猪是广东最著名的特色菜，属粤菜广府菜。",
                            "status": "未完成",
                            "createdAt": "2024-11-09T14:17:26",
                            "updatedAt": "2024-11-09T14:17:26"
                        },
                        "name": "老猪",
                        "path": "/uploads/example.word",
                        "size": 1024,
                        "fileType": "word",
                        "uploadedBy": {
                            "id": 1,
                            "username": "lishuai",
                            "password": "1234",
                            "email": "23156@qq.com",
                            "createdAt": "2024-11-13T13:46:57",
                            "updatedAt": "2024-11-13T13:47:02",
                            "status": 1
                        },
                        "createdAt": "2024-11-13T14:52:09",
                        "updatedAt": "2024-11-13T14:52:09",
                        "description": "这是一个文件",
                        "version": 1,
                        "isCurrent": true
                    },
                    "tagId": {
                        "id": 4,
                        "name": "绝密",
                        "createdAt": "2024-11-14T09:45:26"
                    },
                    "createdAt": "2024-11-14T12:25:16"
                }
            ]
    }
    ```

#### tag_relations 添加

  - 接口说明

    1. 接口功能

       本接口添加文件-标签关联

    2. 请求地址	http://localhost:9090/api/files/tagBinding

    3. 请求方式    POST

  - 请求示例

    | 字段名    | 数据类型 | 是否必填 |
    | --------- | -------- | -------- |
    | fileId.id | int      | 是       |
    | tagId.id  | int      | 是       |

    ```json
    {
        "fileId":{
            "id": 18
        },
        "tagId":{
            "id": 4
        }
    }
    ```

  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": "绑定成功"
    }
    // 失败示例
    {
        "code": 403,
        "msg": "error",
        "data": "该标签或文件已绑定"
    }
    ```

#### tag_relations 删除

  - 接口说明

    1. 接口功能

       本接口删除文件-标签关联

    2. 请求地址	http://localhost:9090/api/files/tagUnbind

    3. 请求方式    DELETE

  - 请求示例

    ```json
    {
        "fileId":{
            "id": 18
        },
        "tagId":{
            "id": 4
        }
    }
    ```

  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": "删除成功"
    }
    // 失败示例
    {
        "code": 403,
        "msg": "error",
        "data": "该数据未存在"
    }
    ```
### Case_file_permissions 用户权限

#### permissions 查询

  - 接口说明

    1. 接口功能

       本接口删除文件-标签关联

    2. 请求地址	http://localhost:9090/api/files/per_add

    3. 请求方式    GET

  - 请求示例

    | 字段名         | 类型   | 是否必填 |
    | -------------- | ------ | -------- |
    | permissionType | String | 否       |

    ```apl
    http://localhost:9090/api/files/per_find?permissionType=编辑
    # 动态查询不填参数就是查询所有
    ```

  - 响应示例

    ```json
    {
        "code": 200,
        "msg": "success",
        "data": {
            "content": [
                {
                    "id": 9,
                    "fileId": {
                        "id": 12,
                        "caseId": {
                            "id": 11,
                            "caseNumber": "10005",
                            "title": "李帅",
                            "description": "烤乳猪是广东最著名的特色菜。",
                            "status": "未完成",
                            "createdAt": "2024-11-09T14:17:26",
                            "updatedAt": "2024-11-09T14:17:26"
                        },
                        "name": "够帅在干嘛",
                        "path": "/uploads/peg.pdf",
                        "size": 2048,
                        "fileType": "pdf",
                        "uploadedBy": {
                            "id": 1,
                            "username": "lishuai",
                            "password": "1234",
                            "email": "23156@qq.com",
                            "createdAt": "2024-11-13T13:46:57",
                            "updatedAt": "2024-11-13T13:47:02",
                            "status": 1
                        },
                        "createdAt": "2024-11-10T17:01:08",
                        "updatedAt": "2024-11-14T16:10:38",
                        "description": "老朱老朱是个狗屎人",
                        "version": 5,
                        "isCurrent": false
                    },
                    "userId": {
                        "id": 1,
                        "username": "lishuai",
                        "password": "1234",
                        "email": "23156@qq.com",
                        "createdAt": "2024-11-13T13:46:57",
                        "updatedAt": "2024-11-13T13:47:02",
                        "status": 1
                    },
                    "permissionType": "编辑",
                    "createdAt": "2024-11-17T15:04:18"
                }
            ]
    }
    ```

#### permissions 添加

  - 接口说明

    1. 接口功能

       本接口删除文件-标签关联

    2. 请求地址	http://localhost:9090/api/files/per_add

    3. 请求方式    POST

  - 请求示例

    | 字段名         | 数据类型 | 是否必填 |
    | -------------- | -------- | -------- |
    | fileId         | int      | 是       |
    | UserId         | int      | 是       |
    | permissionType | String   | 是       |

    ```json
    {
        "fileId": {
            "id": 12
        },
        "userId": {
            "id": 1
        },
        "permissionType": "编辑"
    }
    ```

  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": "添加成功"
    }
    
    // 失败示例
    {
        "code": 403,
        "msg": "error",
        "data": "没有该文件"
    }
    and
    {
        "code": 403,
        "msg": "error",
        "data": "添加失败,该文件已绑定"
    }
    ```

#### permissions 删除

  - 接口说明

    1. 接口功能

       本接口删除文件-标签关联

    2. 请求地址	http://localhost:9090/api/files/per_de

    3. 请求方式    DELETE

  - 请求示例

    ```json
    {
        "fileId": {
            "id": 12
        },
        "userId": {
            "id": 1
        }
    }
    ```

  - 响应示例

    ```json
    // 成功示例
    {
        "code": 200,
        "msg": "success",
        "data": "删除成功"
    }
    // 失败示例
    {
        "code": 403,
        "msg": "error",
        "data": "删除失败没有该用户或文件"
    }
    ```


​       