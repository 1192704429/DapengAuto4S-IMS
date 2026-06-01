/*
 Navicat Premium Data Transfer

 Source Server         : localhost3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : weixiu1

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 29/03/2026 14:25:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户姓名(search-like)',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话(search-like)',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系地址(textarea)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注(textarea)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '李明', '13900139000', '北京市朝阳区建国路88号', '个人客户，首次到店', '2026-03-29 09:00:00');
INSERT INTO `customer` VALUES (2, '王芳', '13900139001', '上海市浦东新区张江路100号', '老客户，定期保养', '2026-03-29 09:00:00');
INSERT INTO `customer` VALUES (3, '张伟', '13900139002', '广州市天河区天河路385号', '企业客户，多辆车维护', '2026-03-29 09:00:00');
INSERT INTO `customer` VALUES (4, '刘艳', '13900139003', '深圳市南山区科技园12栋', '新车首保', '2026-03-29 09:00:00');
INSERT INTO `customer` VALUES (5, '陈强', '13900139004', '杭州市西湖区文三路45号', '事故车维修', '2026-03-29 09:00:00');
INSERT INTO `customer` VALUES (6, '周周', '123123', '新安街道\n海秀路', '123123', '2026-03-29 09:00:00');
INSERT INTO `customer` VALUES (7, '哈哈', '18596235623', '123123123', NULL, '2026-03-29 09:00:00');

-- ----------------------------
-- Table structure for finance_order
-- ----------------------------
DROP TABLE IF EXISTS `finance_order`;
CREATE TABLE `finance_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `finance_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '财务订单编号(gen:OR)',
  `order_type` tinyint(1) NULL DEFAULT NULL COMMENT '订单类型[0:支出,1:收入]',
  `repair_order_id` bigint(20) NULL DEFAULT NULL COMMENT '关联维修单<repair_order:id:order_no>',
  `purchase_order_id` bigint(20) NULL DEFAULT NULL COMMENT '关联采购单<purchase_order:id:purchase_no>',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额(number2)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注(textarea)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(11) NULL DEFAULT 1 COMMENT '状态：0待审核 1已审核',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '财务订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of finance_order
-- ----------------------------
INSERT INTO `finance_order` VALUES (1, 'FN20260108001', 1, 1, NULL, 850.00, '大众朗逸保养费用', '2026-03-29 09:00:00', 1);
INSERT INTO `finance_order` VALUES (2, 'FN20260109002', 1, 2, NULL, 580.00, '丰田凯美瑞刹车片更换费用', '2026-03-29 09:00:00', 1);
INSERT INTO `finance_order` VALUES (3, 'FN20260111003', 1, 4, NULL, 288.00, '比亚迪宋PLUS首保费用', '2026-03-29 09:00:00', 1);
INSERT INTO `finance_order` VALUES (4, 'FN20260105004', 0, NULL, 1, 5850.00, '支付配件采购款', '2026-03-29 09:00:00', 1);
INSERT INTO `finance_order` VALUES (5, 'FN20260107005', 0, NULL, 3, 10800.00, '支付机油采购款', '2026-03-29 09:00:00', 1);
INSERT INTO `finance_order` VALUES (6, 'FN20260109006', 0, NULL, 5, 4400.00, '支付电瓶采购款', '2026-03-29 09:00:00', 1);
INSERT INTO `finance_order` VALUES (7, 'FO20260219232730', 1, 7, NULL, 1860.00, '【工时费】总工时12.00×工程师工时费80.00元/时=960.0000元；【配件费】五菱宏光刹车片×1=150.00元；五菱宏光刹车片×1=150.00元；丰田凯美瑞刹车片×1=320.00元；全合成机油5W-40×1=280.00元；【合计】1860.0000元', '2026-03-29 09:00:00', 1);
INSERT INTO `finance_order` VALUES (8, 'FO20260220001546', 1, 6, NULL, 3068.00, '【工时费】总工时1.00×工程师工时费80.00元/时=80.0000元；【配件费】五菱宏光刹车片×1=150.00元；半合成机油10W-40×1=198.00元；五菱宏光刹车片×1=150.00元；215/55R17轮胎×3=2040.00元；五菱宏光刹车片×1=150.00元；五菱宏光刹车片×1=150.00元；五菱宏光刹车片×1=150.00元；【合计】3068.0000元', '2026-03-29 09:00:00', 1);

-- ----------------------------
-- Table structure for part
-- ----------------------------
DROP TABLE IF EXISTS `part`;
CREATE TABLE `part`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `part_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配件编码(search-like)',
  `part_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配件名称(search-like)',
  `spec_model` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规格型号',
  `brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '所属分类<part_category:id:category_name>',
  `supplier_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商<supplier:id:supplier_name>',
  `unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计量单位',
  `purchase_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '采购价(number2)',
  `sale_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '销售价(number2)',
  `stock` int(11) NULL DEFAULT 0 COMMENT '当前库存(number)',
  `warn_stock` int(11) NULL DEFAULT 0 COMMENT '库存预警(number)',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配件图片(img)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注(textarea)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '配件信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of part
-- ----------------------------
INSERT INTO `part` VALUES (1, 'JYLX-001', '大众朗逸机油滤芯', 'EA211 1.5L', '曼牌', 1, 1, '个', 25.00, 45.00, 100, 20, 'http://localhost:8086/uploads/auto_repair_management/16630c29-9917-4a30-bde7-83983bfbe529-生成汽车图片 (7).png', '适配2023款大众朗逸', '2026-03-29 09:00:00');
INSERT INTO `part` VALUES (2, 'SCP-001', '丰田凯美瑞刹车片', '04465-33430', '博世', 2, 5, '副', 180.00, 320.00, 50, 10, 'http://localhost:8086/uploads/auto_repair_management/e2266dd6-79fa-44c7-8d10-b42540246e65-生成汽车图片 (14).png', '适配2022-2024款凯美瑞', '2026-03-29 09:00:00');
INSERT INTO `part` VALUES (3, 'JY-001', '全合成机油5W-40', '4L/桶', '美孚', 3, 3, '桶', 180.00, 280.00, 80, 15, 'http://localhost:8086/uploads/auto_repair_management/dca279cb-e602-41b7-96fb-addb57c0b68f-生成汽车图片 (9).png', 'SN级，适用大部分汽油车', '2026-03-29 09:00:00');
INSERT INTO `part` VALUES (4, 'DP-001', '12V60AH汽车电瓶', '6-QW-60', '瓦尔塔', 4, 1, '个', 350.00, 550.00, 33, 5, 'http://localhost:8086/uploads/auto_repair_management/f05ecbf0-9ecb-46ec-80ee-e10b49485de7-生成汽车图片 (10).png', '通用型汽车电瓶', '2026-03-29 09:00:00');
INSERT INTO `part` VALUES (5, 'KQLX-001', '本田CR-V空气滤芯', '17220-5AA-A00', '马勒', 5, 1, '个', 45.00, 80.00, 60, 10, 'http://localhost:8086/uploads/auto_repair_management/98b09c13-4efc-43e9-a9e8-ddff1777b666-生成汽车图片 (12).png', '适配2021-2023款CR-V', '2026-03-29 09:00:00');
INSERT INTO `part` VALUES (6, 'LT-001', '215/55R17轮胎', '215/55R17 94V', '米其林', 6, 2, '条', 450.00, 680.00, 20, 5, 'http://localhost:8086/uploads/auto_repair_management/897466b8-a6aa-45ca-8ffb-62b1708ba797-生成汽车图片 (11).png', '适配中型轿车', '2026-03-29 09:00:00');
INSERT INTO `part` VALUES (7, 'JY-002', '半合成机油10W-40', '4L/桶', '嘉实多', 3, 3, '桶', 120.00, 198.00, 70, 15, 'http://localhost:8086/uploads/auto_repair_management/c5bd716a-e8fa-4054-a01e-4351cfb05cdb-生成汽车图片 (13).png', 'SL级，经济型机油', '2026-03-29 09:00:00');
INSERT INTO `part` VALUES (8, 'SCP-002', '五菱宏光刹车片', '6430-3501080', '菲罗多', 2, 5, '副', 85.00, 150.00, 6, 8, 'http://localhost:8086/uploads/auto_repair_management/a9cf3d29-bf49-4b84-b178-15fd6e1c94e1-生成汽车图片 (8).png', '适配五菱宏光全系', '2026-03-29 09:00:00');

-- ----------------------------
-- Table structure for part_category
-- ----------------------------
DROP TABLE IF EXISTS `part_category`;
CREATE TABLE `part_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配件分类名称(search-like)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注(textarea)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '配件分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of part_category
-- ----------------------------
INSERT INTO `part_category` VALUES (1, '机油滤芯', '各类车型机油滤清器', '2026-03-29 09:00:00');
INSERT INTO `part_category` VALUES (2, '刹车片', '前后轮刹车片、刹车盘', '2026-03-29 09:00:00');
INSERT INTO `part_category` VALUES (3, '机油', '全合成、半合成、矿物质机油', '2026-03-29 09:00:00');
INSERT INTO `part_category` VALUES (4, '电瓶', '汽车启动电瓶', '2026-03-29 09:00:00');
INSERT INTO `part_category` VALUES (5, '空气滤芯', '发动机空气滤清器', '2026-03-29 09:00:00');
INSERT INTO `part_category` VALUES (6, '轮胎', '各类规格汽车轮胎', '2026-03-29 09:00:00');

-- ----------------------------
-- Table structure for purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `purchase_order`;
CREATE TABLE `purchase_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `purchase_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购订单编号(gen:OR)',
  `supplier_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商<supplier:id:supplier_name>',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单总金额(number2)',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '采购状态[0:待审核,1:已入库,2:已完成]',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注(textarea)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase_order
-- ----------------------------
INSERT INTO `purchase_order` VALUES (1, 'PO20260105001', 1, 5850.00, 2, '采购常规保养配件', '2026-03-29 09:00:00');
INSERT INTO `purchase_order` VALUES (2, 'PO20260106002', 2, 9000.00, 2, '采购米其林轮胎', '2026-03-29 09:00:00');
INSERT INTO `purchase_order` VALUES (3, 'PO20260107003', 3, 10800.00, 2, '采购各类机油', '2026-03-29 09:00:00');
INSERT INTO `purchase_order` VALUES (4, 'PO20260108004', 5, 7200.00, 0, '采购刹车片备货', '2026-03-29 09:00:00');
INSERT INTO `purchase_order` VALUES (5, 'PO20260109005', 4, 4400.00, 2, '采购汽车电瓶', '2026-03-29 09:00:00');
INSERT INTO `purchase_order` VALUES (6, 'PO1771516139238', 3, 1230.00, 2, '', '2026-03-29 09:00:00');

-- ----------------------------
-- Table structure for purchase_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `purchase_order_detail`;
CREATE TABLE `purchase_order_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `purchase_order_id` bigint(20) NULL DEFAULT NULL COMMENT '关联采购单<purchase_order:id:purchase_no>',
  `part_id` bigint(20) NULL DEFAULT NULL COMMENT '采购配件<part:id:part_name>',
  `purchase_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '采购单价(number2)',
  `quantity` int(11) NULL DEFAULT NULL COMMENT '采购数量(number)',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '小计金额(number2)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购订单详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase_order_detail
-- ----------------------------
INSERT INTO `purchase_order_detail` VALUES (1, 1, 1, 25.00, 100, 2500.00, '2026-03-29 09:00:00');
INSERT INTO `purchase_order_detail` VALUES (2, 1, 5, 45.00, 70, 3150.00, '2026-03-29 09:00:00');
INSERT INTO `purchase_order_detail` VALUES (3, 2, 6, 450.00, 20, 9000.00, '2026-03-29 09:00:00');
INSERT INTO `purchase_order_detail` VALUES (4, 3, 3, 180.00, 40, 7200.00, '2026-03-29 09:00:00');
INSERT INTO `purchase_order_detail` VALUES (5, 3, 7, 120.00, 30, 3600.00, '2026-03-29 09:00:00');
INSERT INTO `purchase_order_detail` VALUES (7, 5, 4, 350.00, 12, 4200.00, '2026-03-29 09:00:00');
INSERT INTO `purchase_order_detail` VALUES (8, 4, 2, 180.00, 40, 7200.00, '2026-03-29 09:00:00');
INSERT INTO `purchase_order_detail` VALUES (9, 6, 4, 350.00, 3, 1050.00, '2026-03-29 09:00:00');
INSERT INTO `purchase_order_detail` VALUES (10, 6, 3, 180.00, 1, 180.00, '2026-03-29 09:00:00');

-- ----------------------------
-- Table structure for repair_detail
-- ----------------------------
DROP TABLE IF EXISTS `repair_detail`;
CREATE TABLE `repair_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `repair_order_id` bigint(20) NULL DEFAULT NULL COMMENT '关联工单<repair_order:id:order_no>',
  `work_hours` decimal(10, 2) NULL DEFAULT NULL COMMENT '维修工时(number2)',
  `repair_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维修内容(textarea)',
  `tech_report` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '技术说明(textarea)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '维修详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair_detail
-- ----------------------------
INSERT INTO `repair_detail` VALUES (1, 1, 2.00, '更换美孚5W-40全合成机油4L，更换曼牌机油滤芯，更换空气滤芯，检查胎压，添加玻璃水', '保养后车辆运行正常，各项指标符合标准', '2026-03-29 09:00:00');
INSERT INTO `repair_detail` VALUES (2, 2, 1.50, '拆卸前轮，更换博世刹车片，打磨刹车盘，调试刹车系统，路试刹车效果', '刹车片厚度已低于2mm，更换后刹车异响消除，制动效果良好', '2026-03-29 09:00:00');
INSERT INTO `repair_detail` VALUES (3, 3, 4.00, '读取故障码为P0300，检查火花塞、点火线圈，发现3缸点火线圈故障，更换点火线圈', '正在维修中，已更换点火线圈，待路试验证', '2026-03-29 09:00:00');
INSERT INTO `repair_detail` VALUES (4, 4, 1.00, '更换原厂机油滤芯，添加比亚迪专用机油，检查电瓶电压，紧固各管路接头', '首保完成，电瓶电压12.6V，状态良好，车辆无异常', '2026-03-29 09:00:00');
INSERT INTO `repair_detail` VALUES (5, 5, 3.00, '拆卸前后轮，更换菲罗多刹车片，检查刹车分泵，拆卸右前轮胎', '刹车片严重磨损，已更换，轮胎鼓包严重，需更换新轮胎', '2026-03-29 09:00:00');
INSERT INTO `repair_detail` VALUES (6, 7, 5.00, '修下车蜡', '修车 修车', '2026-03-29 09:00:00');
INSERT INTO `repair_detail` VALUES (7, 7, 4.00, '123123123', '', '2026-03-29 09:00:00');
INSERT INTO `repair_detail` VALUES (8, 7, 3.00, '123231123', '', '2026-03-29 09:00:00');
INSERT INTO `repair_detail` VALUES (9, 6, 1.00, '1', '1', '2026-03-29 09:00:00');
INSERT INTO `repair_detail` VALUES (11, 9, 2.00, '修轮胎', '', '2026-03-29 09:00:00');

-- ----------------------------
-- Table structure for repair_item_category
-- ----------------------------
DROP TABLE IF EXISTS `repair_item_category`;
CREATE TABLE `repair_item_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目分类名称(search-like)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注(textarea)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '维修项目分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair_item_category
-- ----------------------------
INSERT INTO `repair_item_category` VALUES (1, '常规保养', '机油、机滤、空滤更换等基础保养', '2026-03-29 09:00:00');
INSERT INTO `repair_item_category` VALUES (2, '底盘维修', '刹车片、减震器、悬挂系统维修', '2026-03-29 09:00:00');
INSERT INTO `repair_item_category` VALUES (3, '发动机维修', '发动机故障检修、零部件更换', '2026-03-29 09:00:00');
INSERT INTO `repair_item_category` VALUES (4, '电器系统', '电瓶、车灯、中控系统维修', '2026-03-29 09:00:00');
INSERT INTO `repair_item_category` VALUES (5, '事故修复', '钣金、喷漆、事故车维修', '2026-03-29 09:00:00');

-- ----------------------------
-- Table structure for repair_order
-- ----------------------------
DROP TABLE IF EXISTS `repair_order`;
CREATE TABLE `repair_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维修工单编号(gen:OR)',
  `customer_id` bigint(20) NULL DEFAULT NULL COMMENT '关联客户<customer:id:customer_name>',
  `vehicle_id` bigint(20) NULL DEFAULT NULL COMMENT '关联车辆<vehicle:id:plate_number>',
  `service_user_id` bigint(20) NULL DEFAULT NULL COMMENT '接待客服<sys_user:id:real_name>',
  `engineer_user_id` bigint(20) NULL DEFAULT NULL COMMENT '维修工程师<sys_user:id:real_name>',
  `fault_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '故障描述(textarea)',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '工单状态[0:待派单,1:维修中,2:已完成]',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `repair_item_category_id` bigint(20) NULL DEFAULT NULL COMMENT '关联维修项目分类ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '维修工单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair_order
-- ----------------------------
INSERT INTO `repair_order` VALUES (1, 'OR20260108001', 1, 1, 2, 3, '车辆行驶15000公里，需要做常规保养，更换机油、机滤、空滤', 2, '2026-03-29 09:00:00', NULL);
INSERT INTO `repair_order` VALUES (2, 'OR20260109002', 2, 2, 2, 3, '刹车有异响，检查刹车片磨损情况，需要更换前刹车片', 2, '2026-03-29 09:00:00', NULL);
INSERT INTO `repair_order` VALUES (3, 'OR20260110003', 3, 3, 6, 4, '车辆怠速抖动，发动机故障灯亮，需要检修发动机', 1, '2026-03-29 09:00:00', NULL);
INSERT INTO `repair_order` VALUES (4, 'OR20260111004', 4, 5, 6, 3, '新车首保，更换机油、机滤，检查电瓶状态', 2, '2026-03-29 09:00:00', NULL);
INSERT INTO `repair_order` VALUES (5, 'OR20260112005', 5, 6, 2, 4, '面包车刹车失灵，更换刹车片，检查刹车系统，右前轮胎鼓包需更换', 1, '2026-03-29 09:00:00', NULL);
INSERT INTO `repair_order` VALUES (6, 'OR1771510071913', 5, 6, 6, 3, '123123123', 2, '2026-03-29 09:00:00', NULL);
INSERT INTO `repair_order` VALUES (7, 'OR1771510737925', 5, 6, 6, 3, '123123', 2, '2026-03-29 09:00:00', NULL);
INSERT INTO `repair_order` VALUES (8, 'OR1771511221023', 7, 8, 6, 4, '151651', 1, '2026-03-29 09:00:00', NULL);
INSERT INTO `repair_order` VALUES (9, 'OR1771518617876', 5, 6, 2, 3, '12312312', 1, '2026-03-29 09:00:00', 3);

-- ----------------------------
-- Table structure for repair_part
-- ----------------------------
DROP TABLE IF EXISTS `repair_part`;
CREATE TABLE `repair_part`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `repair_order_id` bigint(20) NULL DEFAULT NULL COMMENT '关联工单<repair_order:id:order_no>',
  `part_id` bigint(20) NULL DEFAULT NULL COMMENT '使用配件<part:id:part_name>',
  `use_qty` int(11) NULL DEFAULT NULL COMMENT '使用数量(number)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '维修配件使用' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair_part
-- ----------------------------
INSERT INTO `repair_part` VALUES (1, 1, 1, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (2, 1, 3, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (3, 1, 5, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (4, 2, 2, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (5, 4, 7, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (6, 5, 8, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (7, 5, 6, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (8, 6, 8, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (9, 6, 7, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (10, 7, 8, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (11, 7, 8, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (12, 7, 2, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (13, 7, 3, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (14, 6, 8, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (15, 6, 6, 3, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (16, 6, 8, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (17, 6, 8, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (19, 6, 8, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (20, 9, 8, 1, '2026-03-29 09:00:00');
INSERT INTO `repair_part` VALUES (21, 9, 3, 1, '2026-03-29 09:00:00');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商名称(search-like)',
  `contact_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址(textarea)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注(textarea)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, '北京汽车配件有限公司', '刘强', '13700137000', '北京市丰台区汽配城5号楼', '主营大众、奥迪配件', '2026-03-29 09:00:00');
INSERT INTO `supplier` VALUES (2, '上海轮胎销售有限公司', '王丽', '13700137001', '上海市普陀区真南路888号', '米其林、普利司通轮胎总代理', '2026-03-29 09:00:00');
INSERT INTO `supplier` VALUES (3, '广州机油批发商行', '陈明', '13700137002', '广州市白云区机场路1200号', '各类品牌机油、滤芯', '2026-03-29 09:00:00');
INSERT INTO `supplier` VALUES (4, '深圳汽车电子科技有限公司', '赵刚', '13700137003', '深圳市宝安区福永街道', '行车记录仪、导航仪', '2026-03-29 09:00:00');
INSERT INTO `supplier` VALUES (5, '杭州刹车片供应中心', '孙丽', '13700137004', '杭州市拱墅区石祥路589号', '博世、菲罗多刹车片', '2026-03-29 09:00:00');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录账号(search-like)',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名(search-like)',
  `role_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户角色[admin:管理员,service:客服,engineer:维修工程师,finance:财务]',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号(search-like)',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像(img)',
  `engineer_hour_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '工程师工时费(number2)',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '账号状态[0:停用,1:启用]',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '123456', '张三', 'admin', '13800138000', NULL, NULL, 1, '2026-03-29 09:00:00');
INSERT INTO `sys_user` VALUES (2, 'service001', '123456', '李四', 'service', '13800138001', NULL, NULL, 1, '2026-03-29 09:00:00');
INSERT INTO `sys_user` VALUES (3, 'engineer001', '123456', '王五', 'engineer', '13800138002', NULL, 80.00, 1, '2026-03-29 09:00:00');
INSERT INTO `sys_user` VALUES (4, 'engineer002', '123456', '赵六', 'engineer', '13800138003', NULL, 90.00, 1, '2026-03-29 09:00:00');
INSERT INTO `sys_user` VALUES (5, 'finance001', '123456', '钱七', 'finance', '13800138004', NULL, NULL, 1, '2026-03-29 09:00:00');
INSERT INTO `sys_user` VALUES (6, 'service002', '123456', '孙八', 'service', '13800138005', NULL, NULL, 1, '2026-03-29 09:00:00');

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` bigint(20) NULL DEFAULT NULL COMMENT '所属客户<customer:id:customer_name>',
  `plate_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车牌号(search-like)',
  `vin_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车架号(search-like)',
  `engine_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发动机号',
  `car_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆照片(img)',
  `brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆品牌',
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆型号',
  `vehicle_type` tinyint(2) NULL DEFAULT NULL COMMENT '车辆类型[0:轿车,1:SUV,2:面包车,3:其他]',
  `color` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车辆颜色',
  `purchase_date` datetime NULL DEFAULT NULL COMMENT '购车日期(time)',
  `mileage` int(11) NULL DEFAULT NULL COMMENT '当前里程(number)',
  `insurance_company` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '保险公司',
  `insurance_expire_time` datetime NULL DEFAULT NULL COMMENT '保险到期时间(time)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注(textarea)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '车辆档案' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicle
-- ----------------------------
INSERT INTO `vehicle` VALUES (1, 1, '京A12345', 'LVVDB12A8MC000001', '12345678901', 'http://localhost:8086/uploads/auto_repair_management/2f139388-78e6-4d48-9ff1-106ec7bdf697-生成汽车图片 (6).png', '大众', '朗逸', 0, '白色', '2024-01-15 00:00:00', 15000, '平安保险', '2025-01-14 23:59:59', '日常家用车', '2026-03-29 09:00:00');
INSERT INTO `vehicle` VALUES (2, 2, '沪B67890', 'LSVAM4187NE000002', '98765432102', 'http://localhost:8086/uploads/auto_repair_management/8b9ced6a-61aa-42d6-bfd4-0d55ad24edd8-生成汽车图片 (4).png', '丰田', '凯美瑞', 0, '黑色', '2023-05-20 00:00:00', 32000, '人保财险', '2024-05-19 23:59:59', '商务用车', '2026-03-29 09:00:00');
INSERT INTO `vehicle` VALUES (3, 3, '粤A56789', 'LJGGA215XNS000003', '45678901233', 'http://localhost:8086/uploads/auto_repair_management/6eb7aca8-1e13-4237-a80a-be0296010387-生成汽车图片 (5).png', '本田', 'CR-V', 1, '黑色', '2022-08-10 00:00:00', 58000, '太平洋保险', '2023-08-09 23:59:59', 'SUV，经常跑长途', '2026-03-29 09:00:00');
INSERT INTO `vehicle` VALUES (4, 3, '粤A98765', 'WDBJG70J6YA000004', '78901234564', 'http://localhost:8086/uploads/auto_repair_management/267870f1-5a85-4993-8db1-68e43573452a-生成汽车图片 (3).png', '奔驰', 'E300L', 0, '白色', '2021-12-05 00:00:00', 85000, '平安保险', '2022-12-04 23:59:59', '公司高管用车', '2026-03-29 09:00:00');
INSERT INTO `vehicle` VALUES (5, 4, '粤B11223', 'LFPXH2A5XNF000005', '23456789015', 'http://localhost:8086/uploads/auto_repair_management/ce954ca0-4b6c-45e2-b6e1-f78213f6e682-生成汽车图片 (2).png', '比亚迪', '宋PLUS', 1, '蓝色', '2024-03-18 00:00:00', 8000, '太平保险', '2025-03-17 23:59:59', '新能源汽车', '2026-03-29 09:00:00');
INSERT INTO `vehicle` VALUES (6, 5, '浙A88990', 'LZWACAGA7M8000006', '89012345676', 'http://localhost:8086/uploads/auto_repair_management/b5fc1a65-a35e-463e-9eb8-811c22e9a3ab-生成汽车图片 (1).png', '五菱', '宏光', 2, '银色', '2020-07-22 00:00:00', 120000, '大地保险', '2021-07-21 23:59:59', '拉货用面包车，有轻微事故', '2026-03-29 09:00:00');
INSERT INTO `vehicle` VALUES (7, 4, '粤BSL079', 'MDK13245641115436', NULL, 'http://localhost:8086/uploads/auto_repair_management/0306c607-3901-4247-bec2-74b0e23744e2-生成汽车图片.png', 'BMW', '325LI', 0, '黑色', '2024-12-11 00:00:00', 8536, '中国平安', '2026-03-26 00:00:00', NULL, '2026-03-29 09:00:00');
INSERT INTO `vehicle` VALUES (8, 7, '湘AE5689', '123', '334344223', '', '', '', 2, '', NULL, NULL, '', NULL, '', '2026-03-29 09:00:00');

SET FOREIGN_KEY_CHECKS = 1;
