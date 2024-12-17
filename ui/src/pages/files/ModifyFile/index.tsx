import { CopyFilled, FolderFilled, FileEditOutlined } from "@ant-design/icons";
import { Breadcrumb, Button, Form, Input, Select, Modal, message } from "antd";
import { useState } from "react";

const ModifyFile = () => {
    const [form] = Form.useForm();
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [selectedFile, setSelectedFile] = useState(null);

    // 模拟档案数据
    const fileOptions = [
        { value: 1, name: '档案1', type: '类型1', description: '档案描述1' },
        { value: 2, name: '档案2', type: '类型2', description: '档案描述2' },
    ];

    const handleFileChange = (value) => {
        const file = fileOptions.find(f => f.value === value);
        if (file) {
            form.setFieldsValue(file);
            setSelectedFile(file);
        }
    };

    const showModal = () => {
        setIsModalVisible(true);
    };

    const handleOk = () => {
        // 提交修改的档案数据
        const updatedData = form.getFieldsValue();
        console.log('修改后的档案数据:', updatedData);
        message.success('档案修改成功！');
        setIsModalVisible(false);
        form.resetFields();
    };

    const handleCancel = () => {
        setIsModalVisible(false);
    };

    return (
        <>
            <div style={{ padding: '24px', background: '#fff' }}>
                <h1 style={{ textAlign: 'center' }}>档案修改</h1>
                <h2>选择档案</h2>
                <Select 
                    placeholder="选择要修改的档案" 
                    onChange={handleFileChange} 
                    style={{ width: '300px', marginBottom: '20px' }}
                >
                    {fileOptions.map(file => (
                        <Select.Option key={file.value} value={file.value}>
                            {file.name}
                        </Select.Option>
                    ))}
                </Select>

                <h2>档案信息</h2>
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

                    <Form.Item name="description" label="描述">
                        <Input.TextArea rows={4} placeholder="请输入档案描述" />
                    </Form.Item>

                    <Form.Item>
                        <Button type="primary" onClick={showModal}>
                            提交
                        </Button>
                    </Form.Item>
                </Form>

                <Modal title="确认修改" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel}>
                    <p>您确定要提交修改吗？</p>
                </Modal>
            </div>
        </>
    );
};

export default ModifyFile;
