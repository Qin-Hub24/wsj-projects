import { FileExclamationFilled, FolderFilled, DeleteFilled } from "@ant-design/icons";
import { Breadcrumb, Button, Select, Modal, message } from "antd";
import { useState } from "react";

const DeleteFile = () => {
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [isSecondModalVisible, setIsSecondModalVisible] = useState(false);
    const [selectedFile, setSelectedFile] = useState(null);
    const [fileDetails, setFileDetails] = useState(null);

    // 模拟档案数据
    const fileOptions = [
        { value: 1, name: '档案1', type: '类型1', description: '档案描述1' },
        { value: 2, name: '档案2', type: '类型2', description: '档案描述2' },
    ];

    const handleFileChange = (value) => {
        const file = fileOptions.find(f => f.value === value);
        setSelectedFile(value);
        setFileDetails(file);
    };

    const showFirstModal = () => {
        setIsModalVisible(true);
    };

    const handleFirstOk = () => {
        setIsModalVisible(false);
        setIsSecondModalVisible(true);
    };

    const handleSecondOk = () => {
        // 执行删除操作
        console.log('删除档案 ID:', selectedFile);
        message.success('档案删除成功！');
        setIsSecondModalVisible(false);
        setSelectedFile(null);
        setFileDetails(null);
    };

    const handleFirstCancel = () => {
        setIsModalVisible(false);
    };

    const handleSecondCancel = () => {
        setIsSecondModalVisible(false);
    };

    return (
        <>
            <div style={{ padding: '24px', background: '#fff' }}>
                <h1 style={{ textAlign: 'center' }}>档案删除</h1>
                <h2>选择档案</h2>
                <Select
                    placeholder="选择要删除的档案"
                    onChange={handleFileChange}
                    style={{ width: '300px', marginBottom: '20px' }}
                >
                    {fileOptions.map(file => (
                        <Select.Option key={file.value} value={file.value}>
                            {file.name}
                        </Select.Option>
                    ))}
                </Select>

                {/* 显示所选档案的详细信息，横向展示 */}
                {fileDetails && (
                    <div style={{ display: 'flex',}}>
                        <div style={{ marginRight: '20px' }}>
                            <strong>档案名称:</strong> {fileDetails.name}
                        </div>
                        <div style={{ marginRight: '20px' }}>
                            <strong>档案类型:</strong> {fileDetails.type}
                        </div>
                        <div>
                            <strong>档案描述:</strong> {fileDetails.description}
                        </div>
                    </div>
                )}
                <div style={{ marginTop: '20px' }}>
                    <Button type="primary" onClick={showFirstModal} disabled={!selectedFile}>
                        删除档案
                    </Button>
                </div>

                <Modal
                    title="确认删除"
                    visible={isModalVisible}
                    onOk={handleFirstOk}
                    onCancel={handleFirstCancel}
                >
                    <p>您确定要删除该档案吗？</p>
                </Modal>

                <Modal
                    title="再次确认"
                    visible={isSecondModalVisible}
                    onOk={handleSecondOk}
                    onCancel={handleSecondCancel}
                >
                    <p>您真的要删除该档案吗？此档案将无法恢复！</p>
                </Modal>
            </div>
        </>
    );
};

export default DeleteFile;
