import { UploadOutlined } from '@ant-design/icons';
import { Button, DatePicker, Form, Input, message, Modal, Select, Upload } from 'antd';
import { useState } from 'react';

const AddFile = () => {
  const [form] = Form.useForm();
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [formData, setFormData] = useState(null);

  const showModal = () => {
    setFormData(form.getFieldsValue());
    setIsModalVisible(true);
  };

  const handleOk = () => {
    // 提交表单数据
    console.log('Submitted values:', formData);
    message.success('档案添加成功！');
    setIsModalVisible(false);
    form.resetFields();
  };

  const handleCancel = () => {
    setIsModalVisible(false);
  };

  return (
    <>
      <div style={{ padding: '24px', background: '#fff' }}>
        <h1 style={{ textAlign: 'center' }}>档案添加</h1>
        <h2>添加档案</h2>
        <Form form={form} layout="vertical">
          <Form.Item
            name="name"
            label="档案名称"
            rules={[{ required: true, message: '请输入档案名称' }]}
          >
            <Input placeholder="请输入档案名称" />
          </Form.Item>

          <Form.Item
            name="type"
            label="档案类型"
            rules={[{ required: true, message: '请选择档案类型' }]}
          >
            <Select placeholder="选择档案类型">
              <Select.Option value="type1">类型 1</Select.Option>
              <Select.Option value="type2">类型 2</Select.Option>
              {/* 可以添加更多选项 */}
            </Select>
          </Form.Item>

          <Form.Item
            name="date"
            label="创建日期"
            rules={[{ required: true, message: '请选择创建日期' }]}
          >
            <DatePicker />
          </Form.Item>

          <Form.Item name="description" label="描述">
            <Input.TextArea rows={5} placeholder="请输入档案描述" />
          </Form.Item>

          <Form.Item name="attachment" label="附件上传">
            <Upload beforeUpload={() => false}>
              <Button icon={<UploadOutlined />}>点击上传文件</Button>
            </Upload>
          </Form.Item>

          <Form.Item>
            <Button type="primary" onClick={showModal}>
              提交
            </Button>
            <Button style={{ marginLeft: '8px' }} onClick={() => form.resetFields()}>
              取消
            </Button>
          </Form.Item>
        </Form>

        <Modal title="确认提交" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel}>
          <p>您确定要提交该档案吗？</p>
        </Modal>
      </div>
    </>
  );
};

export default AddFile;
