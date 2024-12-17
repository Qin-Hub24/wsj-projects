// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';
import { TableListItem } from './data';

/** 获取规则列表 GET /api/rule */
export async function rule(
  params: {
    // query
    /** 当前的页码 */
    current?: number;
    /** 页面的容量 */
    pageSize?: number;
    id?: number;
  },
  options?: { [key: string]: any },
) {
  console.log(params, options);
  // 接口只有根据id去查
  if (params?.id) {
    try {
      const data = await request<{
        data: TableListItem[];
        /** 列表的内容总数 */
        total?: number;
        success?: boolean;
      }>('http://192.168.0.55:9090/api/user/' + params?.id, {
        method: 'GET',
        params: {
          ...params,
        },
        ...(options || {}),
      });
      console.log(data, 'data----');
      return {
        current: 1,
        data: [data],
        pageSize: 9999,
        success: true,
        total: 9999,
      };
    } catch (error) {
      return {
        current: 1,
        data: [],
        pageSize: 9999,
        success: true,
        total: 9999,
      };
    }
  } else {
    const data = await request<{
      data: TableListItem[];
      /** 列表的内容总数 */
      total?: number;
      success?: boolean;
    }>('http://192.168.0.55:9090/api/user/allUsers', {
      method: 'GET',
      params: {
        ...params,
      },
      ...(options || {}),
    });
    console.log(data, 'data----');
    return {
      current: 1,
      data,
      pageSize: 9999,
      success: true,
      total: 9999,
    };
  }
}

/** 新建规则 PUT /api/rule */
export async function updateRule(data: { [key: string]: any }, options?: { [key: string]: any }) {
  return request<TableListItem>('http://192.168.0.55:9090/api/user', {
    data,
    method: 'PUT',
    ...(options || {}),
  });
}

/** 新建规则 POST /api/rule */
export async function addRule(data: { [key: string]: any }, options?: { [key: string]: any }) {
  return request<TableListItem>('http://192.168.0.55:9090/api/user', {
    data: data,
    method: 'POST',
    params: {},
    // ...(options || {}),
  });
}

/** 删除规则 DELETE /api/rule */
export async function removeRule(data: any, options?: { [key: string]: any }) {
  return request<Record<string, any>>('http://192.168.0.55:9090/api/user/' + data, {
    method: 'DELETE',
    ...(options || {}),
  });
}
