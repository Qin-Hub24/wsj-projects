import { PlusOutlined } from '@ant-design/icons';
import type { ActionType, ProColumns } from '@ant-design/pro-components';
import { PageContainer, ProTable } from '@ant-design/pro-components';
import { Button, message, Modal } from 'antd';
import dayjs from 'dayjs';
import React, { useRef, useState } from 'react';
import OperationModal from './components/CreateForm';
import type { FormValueType } from './components/UpdateForm';
import type { TableListItem, TableListPagination } from './data';
import { addRule, removeRule, rule, updateRule } from './service';
/**
 * 添加节点
 *
 * @param fields
 */

const handleAdd = async (fields: TableListItem) => {
  const hide = message.loading('正在添加');

  try {
    await addRule({ ...fields });
    hide();
    message.success('添加成功');
    return true;
  } catch (error) {
    hide();
    message.error('添加失败请重试！');
    return false;
  }
};
/**
 * 更新节点
 *
 * @param fields
 */

const handleUpdate = async (fields: FormValueType, currentRow?: TableListItem) => {
  const hide = message.loading('正在配置');

  try {
    await updateRule({
      ...currentRow,
      ...fields,
    });
    hide();
    message.success('配置成功');
    return true;
  } catch (error) {
    hide();
    message.error('配置失败请重试！');
    return false;
  }
};
/**
 * 删除节点
 *
 * @param selectedRows
 */

const TableList: React.FC = () => {
  const [done, setDone] = useState<boolean>(false);
  const [open, setVisible] = useState<boolean>(false);
  const actionRef = useRef<ActionType>();

  const [current, setCurrent] = useState<any>(undefined);
  const handleDone = () => {
    setDone(false);
    setVisible(false);
    setCurrent();
  };
  const handleSubmit = async (values: any) => {
    if (values.id) {
      const hide = message.loading('正在修改');
      try {
        await updateRule(values);
        hide();
        message.success('修改成功');
        handleDone();
        if (actionRef.current) {
          actionRef.current.reload();
        }
        return true;
      } catch (error) {
        hide();
        message.error('修改失败请重试！');
        return false;
      }
    } else {
      const hide = message.loading('正在添加');
      try {
        await addRule(values);
        hide();
        message.success('添加成功');
        setVisible(false);
        if (actionRef.current) {
          actionRef.current.reload();
        }
        return true;
      } catch (error) {
        hide();
        message.error('添加失败请重试！');
        return false;
      }
    }
  };

  const handleRemove = async (selectedRows: TableListItem) => {
    console.log(selectedRows, 'selectedRows');

    const hide = message.loading('正在删除');
    if (!selectedRows) return true;

    try {
      await removeRule(selectedRows[0].id);
      hide();
      message.success('删除成功，即将刷新');
      if (actionRef.current) {
        actionRef.current.reload();
      }
      return true;
    } catch (error) {
      hide();
      message.error('删除失败，请重试');
      return false;
    }
  };
  /** 新建窗口的弹窗 */
  const [createModalVisible, handleModalVisible] = useState<boolean>(false);
  /** 分布更新窗口的弹窗 */

  const [updateModalVisible, handleUpdateModalVisible] = useState<boolean>(false);
  const [showDetail, setShowDetail] = useState<boolean>(false);
  const [currentRow, setCurrentRow] = useState<TableListItem>();
  const [selectedRowsState, setSelectedRows] = useState<TableListItem[]>([]);
  /** 国际化配置 */

  // const []

  const columns: ProColumns<TableListItem>[] = [
    {
      title: 'id',
      dataIndex: 'id',
      // tip: 'id是唯一的 key',
      // render: (dom, entity) => {
      //   return (
      //     <a
      //       onClick={() => {
      //         setCurrentRow(entity);
      //         setShowDetail(true);
      //       }}
      //     >
      //       {dom}
      //     </a>
      //   );
      // },
    },
    {
      title: '用户名',
      dataIndex: 'username',
      search: false,

      // valueType: 'textarea',
    },
    {
      title: '邮箱',
      dataIndex: 'email',
      search: false,

      // sorter: true,
      // hideInForm: true,
      // renderText: (val: string) => `${val}万`,
    },
    {
      title: '创建时间',
      dataIndex: 'createdAt',
      search: false,

      render: (text) => dayjs(text).format('YYYY-MM-DD HH:mm:ss'),
    },
    {
      title: '更新时间',
      dataIndex: 'updatedAt',
      search: false,

      render: (text) => dayjs(text).format('YYYY-MM-DD HH:mm:ss'),
    },
    {
      title: '状态',
      dataIndex: 'status',
      // hideInForm: true,
      search: false,
      valueEnum: {
        0: {
          text: '禁用',
          status: 'Default',
        },
        1: {
          text: '正常',
          status: 'Processing',
        },
        // 2: {
        //   text: '下载',
        //   status: 'Success',
        // },
        // 3: {
        //   text: '异常',
        //   status: 'Error',
        // },
      },
    },
    // {
    //   title: '创建时间',
    //   sorter: true,
    //   dataIndex: 'created_at',
    //   valueType: 'dateTime',
    //   // renderFormItem: (item, { defaultRender, ...rest }, form) => {
    //   //   const status = form.getFieldValue('status');

    //   //   if (`${status}` === '0') {
    //   //     return false;
    //   //   }

    //   //   if (`${status}` === '3') {
    //   //     return <Input {...rest} placeholder="请输入异常原因！" />;
    //   //   }

    //   //   return defaultRender(item);
    //   // },
    // },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => [
        <a
          key="config"
          onClick={() => {
            setVisible(true);
            console.log(record);
            setCurrent(record);

            // handleUpdateModalVisible(true);
            // setCurrentRow(record);
          }}
        >
          修改
        </a>,
        <a
          key="delete"
          onClick={() =>
            Modal.confirm({
              title: '删除任务',
              content: '确定删除？',
              okText: '确认',
              cancelText: '取消',
              onOk: () => handleRemove([record]),
            })
          }
        >
          删除
        </a>,
      ],
    },
  ];

  return (
    <PageContainer
      title={
        <div style={{ display: 'flex' }}>
          <div
            style={{
              width: '3px',
              height: '26px',
              marginTop: '4px',
              background: '#348cd3',
              marginRight: '5px',
              borderRadius: '5px',
            }}
          ></div>
          用户
        </div>
      }
    >
      <ProTable<TableListItem, TableListPagination>
        headerTitle="查询表格"
        actionRef={actionRef}
        rowKey="key"
        search={{
          labelWidth: 25,
          // formItemProps: {
          //   labelAlign: 'left', // 设置左对齐
          // },
        }}
        pagination={false}
        toolBarRender={() => [
          <Button
            type="primary"
            key="primary"
            onClick={() => {
              setVisible(true);
            }}
          >
            <PlusOutlined /> 新建
          </Button>,
        ]}
        request={rule}
        columns={columns}
        // rowSelection={{
        //   onChange: (_, selectedRows) => {
        //     setSelectedRows(selectedRows);
        //   },
        // }}
      />
      {/* {selectedRowsState?.length > 0 && (
        <FooterToolbar
          extra={
            <div>
              已选择{' '}
              <a
                style={{
                  fontWeight: 600,
                }}
              >
                {selectedRowsState.length}
              </a>{' '}
              项 &nbsp;&nbsp;
              <span>
                服务调用次数总计 {selectedRowsState.reduce((pre, item) => pre + item.callNo!, 0)} 万
              </span>
            </div>
          }
        >
          <Button
            onClick={async () => {
              await handleRemove(selectedRowsState);
              setSelectedRows([]);
              actionRef.current?.reloadAndRest?.();
            }}
          >
            批量删除
          </Button>
          <Button type="primary">批量审批</Button>
        </FooterToolbar>
      )} */}
      {/* <ModalForm
        title="新建权限"
        width="400px"
        open={createModalVisible}
        onVisibleChange={handleModalVisible}
        onFinish={async (value) => {
          console.log(value, 'value');
          const success = await handleAdd(value as TableListItem);
          if (success) {
            handleModalVisible(false);
            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
      >
        <ProFormText
          label="文件id"
          rules={[
            {
              required: true,
              message: '文件id为必填项',
            },
          ]}
          width="md"
          name="file_id"
        />
        <ProFormText
          label="用户id"
          rules={[
            {
              required: true,
              message: '用户id为必填项',
            },
          ]}
          width="md"
          name="user_id"
        />
        <ProFormText
          label="权限类型"
          rules={[
            {
              required: true,
              message: '权限类型为必填项',
            },
          ]}
          width="md"
          name="permission_type"
        />
      </ModalForm> */}
      {/* <UpdateForm
        onSubmit={async (value) => {
          console.log(value, 'UpdateForm');

          const success = await handleUpdate(value, currentRow);

          if (success) {
            handleUpdateModalVisible(false);
            setCurrentRow(undefined);

            if (actionRef.current) {
              actionRef.current.reload();
            }
          }
        }}
        onCancel={() => {
          handleUpdateModalVisible(false);
          setCurrentRow(undefined);
        }}
        updateModalVisible={updateModalVisible}
        values={currentRow || {}}
      /> */}

      {/* <Drawer
        width={600}
        open={showDetail}
        onClose={() => {
          setCurrentRow(undefined);
          setShowDetail(false);
        }}
        closable={false}
      >
        {currentRow?.name && (
          <ProDescriptions<TableListItem>
            column={2}
            title={currentRow?.name}
            request={async () => ({
              data: currentRow || {},
            })}
            params={{
              id: currentRow?.name,
            }}
            columns={columns as ProDescriptionsItemProps<TableListItem>[]}
          />
        )}
      </Drawer> */}
      <OperationModal
        done={done}
        open={open}
        current={current}
        onDone={handleDone}
        onSubmit={handleSubmit}
      />
    </PageContainer>
  );
};

export default TableList;
