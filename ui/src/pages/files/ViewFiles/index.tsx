import { FolderFilled, FolderOpenFilled, FileSearchOutlined } from "@ant-design/icons";
import { Breadcrumb, Button, DatePicker, Form, Input, Select, Table, Spin } from "antd";
import { useState } from "react";

const ViewFiles = () => {
    const [form] = Form.useForm();
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(false); // 添加加载状态

    const onFinish = (values) => {
        // 显示加载动画
        setLoading(true);
        // 模拟查询延时
        setTimeout(() => {
            console.log('查询条件:', values);
            // 模拟数据
            const simulatedData = [
                { key: 1, name: '档案1', type: '类型1', date: '2024-01-01', description: '档案描述1' },
                { key: 2, name: '档案2', type: '类型2', date: '2024-02-01', description: '档案描述2' },
            ];

            setData(simulatedData);
            setLoading(false); // 隐藏加载动画
        }, 2000); // 模拟2秒的查询延时
    };

    const columns = [
        {
            title: '档案名称',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: '档案类型',
            dataIndex: 'type',
            key: 'type',
        },
        {
            title: '创建日期',
            dataIndex: 'date',
            key: 'date',
        },
        {
            title: '描述',
            dataIndex: 'description',
            key: 'description',
        },
    ];

    return (
        <>
            <div style={{ padding: '24px', background: '#fff' }}>
                <h1 style={{ textAlign: 'center' }}>档案查询</h1>
                <h2>查询条件</h2>
                <Form form={form} layout="vertical" onFinish={onFinish}>
                    <Form.Item name="name" label="档案名称">
                        <Input placeholder="请输入档案名称" />
                    </Form.Item>

                    <Form.Item name="type" label="档案类型">
                        <Select placeholder="选择档案类型">
                            <Select.Option value="type1">类型 1</Select.Option>
                            <Select.Option value="type2">类型 2</Select.Option>
                            {/* 可以添加更多选项 */}
                        </Select>
                    </Form.Item>

                    <Form.Item name="date" label="创建日期">
                        <DatePicker />
                    </Form.Item>

                    <Form.Item>
                        <Button type="primary" htmlType="submit">
                            查询
                        </Button>
                    </Form.Item>
                </Form>

                <h2>查询结果</h2>
                {loading ? (
                    <Spin size="large" style={{ display: 'block', margin: '20px auto' }} />
                ) : (
                    <Table dataSource={data} columns={columns} />
                )}
            </div>
        </>
    );
};

export default ViewFiles;
