import { ModalForm, ProFormSelect, ProFormText } from '@ant-design/pro-components';
import { Button, Result } from 'antd';
import type { FC } from 'react';
import type { BasicListItemDataType } from '../data.d';
import useStyles from '../style.style';
type OperationModalProps = {
  done: boolean;
  open: boolean;
  current: Partial<BasicListItemDataType> | undefined;
  onDone: () => void;
  onSubmit: (values: BasicListItemDataType) => void;
  children?: React.ReactNode;
};
const OperationModal: FC<OperationModalProps> = (props) => {
  const { styles } = useStyles();
  const { done, open, current, onDone, onSubmit, children } = props;
  if (!open) {
    return null;
  }
  return (
    <ModalForm<BasicListItemDataType>
      open={open}
      title={done ? null : `${current ? '编辑' : '添加'}`}
      className={styles.standardListForm}
      width={640}
      onFinish={async (values) => {
        if (current?.id) {
          onSubmit({
            ...current,
            ...values,
          });
        } else {
          onSubmit(values);
        }
      }}
      initialValues={current}
      submitter={{
        render: (_, dom) => (done ? null : dom),
      }}
      trigger={<>{children}</>}
      modalProps={{
        onCancel: () => onDone(),
        destroyOnClose: true,
        bodyStyle: done
          ? {
              padding: '72px 0',
            }
          : {},
      }}
    >
      {!done ? (
        <>
          <ProFormText
            name="username"
            label="用户名"
            rules={[
              {
                required: true,
                message: '请输入用户名',
              },
            ]}
            placeholder="请输入"
          />
          <ProFormText
            name="password"
            label="密码"
            rules={[
              {
                required: true,
                message: '请输入密码',
              },
            ]}
            placeholder="请输入"
          />
          <ProFormText
            name="email"
            label="邮箱"
            rules={[
              {
                required: true,
                message: '请输入邮箱',
              },
              {
                type: 'email',
                message: '请输入正确的邮箱',
              },
            ]}
            placeholder="请输入"
          />
          <ProFormSelect
            name="status"
            label="状态"
            rules={[
              {
                required: true,
                message: '请选择状态',
              },
            ]}
            options={[
              {
                label: '启用',
                value: 1,
              },
              {
                label: '禁用',
                value: 0,
              },
            ]}
            placeholder="请选择状态"
          />

          {/*
           <ProFormText
            name="username"
            label="用户名"
            rules={[
              {
                required: true,
                message: '请输入用户名',
              },
            ]}
            placeholder="请输入"
          />
          {/*
          {/* <ProFormDateTimePicker
            name="createdAt"
            label="开始时间"
            rules={[
              {
                required: true,
                message: '请选择开始时间',
              },
            ]}
            fieldProps={{
              style: {
                width: '100%',
              },
            }}
            placeholder="请选择"
          />
          <ProFormSelect
            name="owner"
            label="任务负责人"
            rules={[
              {
                required: true,
                message: '请选择任务负责人',
              },
            ]}
            options={[
              {
                label: '付晓晓',
                value: 'xiao',
              },
              {
                label: '周毛毛',
                value: 'mao',
              },
            ]}
            placeholder="请选择管理员"
          />
          <ProFormTextArea
            name="subDescription"
            label="产品描述"
            rules={[
              {
                message: '请输入至少五个字符的产品描述！',
                min: 5,
              },
            ]}
            placeholder="请输入至少五个字符"
          /> */}
        </>
      ) : (
        <Result
          status="success"
          title="操作成功"
          subTitle="一系列的信息描述，很短同样也可以带标点。"
          extra={
            <Button type="primary" onClick={onDone}>
              知道了
            </Button>
          }
          className={styles.formResult}
        />
      )}
    </ModalForm>
  );
};
export default OperationModal;
