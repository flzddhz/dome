package com.demo;

public class Email {
    public static void main(String[] args) {
        //必须要使用apps用户执行
        String P_DATE = "2022-08-22";                       //1-11
//        String FACTORY = "SUNTAK_江门崇达HDI厂";
//        String FACTORY = "SUNTAK_珠海崇达一厂";
//        String FACTORY = "SUNTAK_珠海一厂";
//        String FACTORY = "SUNTAK_江门崇达一厂";
//        String FACTORY = "SUNTAK_深圳崇达";
        String FACTORY = "SUNTAK_金州崇达";

        //获取要发邮件的记录
        String sql=
                " Select ou1.Name L_ORG, "+
                        "        ou2.Name S_ORG, "+
                        "        apps.cux_om_undone_ord_dtl_pkg.get_Org_Name(ool.attribute15) P_ORG,  "+
                        "        hca.account_number CUSTOMER_NUMBER, "+
                        "        hp.party_name CUSTOMER_NAME, "+
                        "		 cux_gqzhou.get_END_CUS_ITEM_NAME(ool.inventory_item_id,ool.attribute15,null) END_CUST_PN, " +
                        "		 Cux_Common_Pkg.get_item_prms(ool.attribute15,ool.inventory_item_id,'END_CUS_DES','-1') END_CUST_NAME, " +
                        "        to_char(ooh.order_number) C_ORDER, "+
                        "        ool.line_number || '.' || ool.shipment_number L_ORDER, "+
                        "        ool.ordered_item ITEM, "+
                        "        ool.ordered_quantity QUANTITY, "+
                        "   	 nvl(ool.cust_po_number, ooh.cust_po_number) CUST_PO_NUMBER, "+
                        " 		 cux_common_pkg.get_Cust_Item_Desc(ool.inventory_item_id, "+
                        "                                ool.attribute15) CUST_DESC, "+
                        "        mail.send_email_address SEND_EMAIL_ADDRESS, "+
                        "        mail.recevice_address RECEVICE_ADDRESS, "+
                        "        mail.cc_address CC_ADDRESS, "+
                        "        mail.subject SUBJECT, "+
                        "        mail.mail_content MAIL_CONTENT, " +
                        "        mail.subject_s SUBJECT_S, "+
                        "		 mail.attach_name ATTACH_NAME, "+
                        "        mail.attribute1 ATTRIBUTE1, "+
                        "        cux_common_zzhong_pkg.get_coc_life_period(p_ordered_item => ool.ordered_item,"+
                        "        p_order_number => to_char(ooh.order_number) || '-' ||ool.line_number || '.' ||ool.shipment_number," +
                        "		 p_org_id => ool.attribute15,p_date => TO_DATE('"+P_DATE+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) life_period " +
                        "   From cux_oe_gcshipschnum_assign coga, "+
                        "        oe_order_lines_all         ool, "+
                        "        oe_order_headers_all       ooh, "+
                        "        hz_cust_accounts           hca, "+
                        "        hz_parties                 hp, "+
                        "        cux_oe_bzxx_rsv_result     brr, "+
                        "        cux_oe_bzxx_packing        bp, "+
                        "        hr_all_organization_units  ou1, "+
                        "        hr_all_organization_units  ou2, "+
                        "        jtf_rs_salesreps           jrs, "+
                        "        jtf_rs_defresources_v      jrdv, "+
                        "        CUX_CUST_SHIP_MAIL         mail "+
                        "  Where 1 = 1 "+
                        "    And (ool.unit_selling_price <> 0 Or "+
                        "        (ool.unit_selling_price = 0 And nvl(bp.mix_flag, 'N') = 'N')) "+
                        "    And apps.cux_oe_inventory_pkg.get_line_status(ool.line_id) In "+
                        "        ('已关闭', "+
                        "         '分批发运/确认挑库', "+
                        "         '收入已确认', "+
                        "         '等待收入确认', "+
                        "         '部分收入确认', "+
                        "         '已发运') "+
                        "    And nvl(ool.SHIPPING_METHOD_CODE, 'XXXXX') <> '000001_港车_R_D2P' "+
                        "    And coga.line_id = ool.line_id "+
                        "    And ool.header_id = ooh.header_id "+
                        "    And ooh.sold_to_org_id = hca.cust_account_id "+
                        "    And hca.party_id = hp.party_id "+
                        "    And brr.line_id = coga.line_id "+
                        "    And bp.packing_id = brr.packing_id "+
                        "    And coga.bc_ship_qty > 0 "+
                        "    And ou1.organization_id = ool.org_id "+
                        "    And ou2.organization_id = ool.ship_from_org_id "+
                        "    And ooh.salesrep_id = jrs.salesrep_id "+
                        "    And ooh.org_id = jrs.org_id "+
                        "	 And ool.attribute15 = mail.org_Id "+
                        "    And jrs.resource_id = jrdv.resource_id "+
                        "    And hca.account_number = mail.customer_number "+
                        "    And apps.cux_om_undone_ord_dtl_pkg.get_Org_Name(ool.attribute15) like '%"+FACTORY+"%' "+
                        "    And Not Exists (Select 1 "+
                        "           From wsh_delivery_details wdd "+
                        "          Where wdd.source_code = 'OE' "+
                        "            And wdd.released_status = 'S' "+
                        "            And wdd.source_line_id = ool.line_id) "+
                        "    And Not Exists (Select 1 "+
                        "           From cux_oe_bzxx_rsv_result r "+
                        "          Where r.packing_id = brr.packing_id "+
                        "            And r.org_id = brr.org_id "+
                        "            And r.line_id < brr.line_id "+
                        "            And apps.CUX_CUXOESHIPPED_PKG.compare_order_line_number(r.line_id, "+
                        "                                                                    brr.line_id) = 'Y' "+
                        "            AND r.mix_flag = 'Y') "+
                        "    And apps.CUX_CUXOESHIPPED_PKG.get_comparison_flag(ool.line_id, "+
                        "                                                      ool.ship_from_org_id, "+
                        "                                                      TO_DATE('"+P_DATE+" 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), "+
                        "                                                      TO_DATE('"+P_DATE+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) = 'Y' "+
                        "    And Exists "+
                        "  (Select 1 "+
                        "           From inv.MTL_MATERIAL_TRANSACTIONS mmt "+
                        "          Where 1 = 1 "+
                        "            And mmt.organization_id = ool.ship_from_org_id "+
                        "            And mmt.transaction_type_id = 52 "+
                        "            And mmt.trx_source_line_id = ool.line_id "+
                        "            And mmt.transaction_quantity > 0 "+
                        "            And mmt.transaction_date >= "+
                        "                NVL(TO_DATE('"+P_DATE+" 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), "+
                        "                    Sysdate) "+
                        "            And mmt.transaction_date <= "+
                        "                NVL(TO_DATE('"+P_DATE+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), "+
                        "                    Sysdate)) "+
                        " union "+
                        " Select ou1.Name L_ORG, "+
                        "        ou2.Name S_ORG, "+
                        "        apps.cux_om_undone_ord_dtl_pkg.get_Org_Name(ool.attribute15) P_ORG,  "+
                        "        hca.account_number CUSTOMER_NUMBER, "+
                        "        hp.party_name CUSTOMER_NAME,  "+
                        "		 cux_gqzhou.get_END_CUS_ITEM_NAME(ool.inventory_item_id,ool.attribute15,null) END_CUST_PN, " +
                        "		 Cux_Common_Pkg.get_item_prms(ool.attribute15,ool.inventory_item_id,'END_CUS_DES','-1') END_CUST_NAME, " +
                        "        to_char(ooh.order_number) C_ORDER,  "+
                        "        ool.line_number || '.' || ool.shipment_number L_ORDER,   "+
                        "        ool.ordered_item ITEM, "+
                        "        ool.ordered_quantity QUANTITY, "+
                        "   	 nvl(ool.cust_po_number, ooh.cust_po_number) CUST_PO_NUMBER, "+
                        " 		 cux_common_pkg.get_Cust_Item_Desc(ool.inventory_item_id, "+
                        "                                ool.attribute15) CUST_DESC, "+
                        "        mail.send_email_address SEND_EMAIL_ADDRESS, "+
                        "        mail.recevice_address RECEVICE_ADDRESS, "+
                        "        mail.cc_address CC_ADDRESS, "+
                        "        mail.subject SUBJECT, "+
                        "        mail.mail_content MAIL_CONTENT, " +
                        "        mail.subject_s SUBJECT_S, "+
                        "		 mail.attach_name ATTACH_NAME, "+
                        "        mail.attribute1 ATTRIBUTE1, "+
                        "        cux_common_zzhong_pkg.get_coc_life_period(p_ordered_item => ool.ordered_item,"+
                        "        p_order_number => to_char(ooh.order_number) || '-' ||ool.line_number || '.' ||ool.shipment_number," +
                        "		 p_org_id => ool.attribute15,p_date => TO_DATE('"+P_DATE+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) life_period " +
                        "   From cux_oe_gcshipschnum_assign coga, "+
                        "        oe_order_lines_all         ool, "+
                        "        oe_order_headers_all       ooh, "+
                        "        hz_cust_accounts           hca, "+
                        "        hz_parties                 hp, "+
                        "        cux_oe_bzxx_rsv_result     brr, "+
                        "        cux_oe_bzxx_packing        bp, "+
                        "        cux_oe_multi_card_lines_v  mcl, "+
                        "        hr_all_organization_units  ou1, "+
                        "        hr_all_organization_units  ou2, "+
                        "        jtf_rs_salesreps           jrs, "+
                        "        jtf_rs_defresources_v      jrdv, "+
                        "        CUX_CUST_SHIP_MAIL         mail "+
                        "  Where 1 = 1 "+
                        "    And (ool.unit_selling_price <> 0 Or "+
                        "        (ool.unit_selling_price = 0 And nvl(bp.mix_flag, 'N') = 'N')) "+
                        "    And apps.cux_oe_inventory_pkg.get_line_status(ool.line_id) In "+
                        "        ('已关闭', "+
                        "         '分批发运/确认挑库', "+
                        "         '收入已确认', "+
                        "         '等待收入确认', "+
                        "         '部分收入确认', "+
                        "         '已发运') "+
                        "    And nvl(ool.SHIPPING_METHOD_CODE, 'XXXXX') = '000001_港车_R_D2P' "+
                        "    And coga.line_id = ool.line_id "+
                        "    And ool.header_id = ooh.header_id "+
                        "    And ooh.sold_to_org_id = hca.cust_account_id "+
                        "    And hca.party_id = hp.party_id "+
                        "    And brr.line_id = coga.line_id "+
                        "    And bp.packing_id = brr.packing_id "+
                        "    And coga.bc_ship_qty > 0 "+
                        "    And mcl.line_id = brr.line_id "+
                        "    And mcl.packing_id = brr.packing_id "+
                        "    And ou1.organization_id = ool.org_id "+
                        "    And ou2.organization_id = ool.ship_from_org_id "+
                        "    And ooh.salesrep_id = jrs.salesrep_id "+
                        "    And ooh.org_id = jrs.org_id "+
                        "	 And ool.attribute15 = mail.org_Id "+
                        "    And jrs.resource_id = jrdv.resource_id "+
                        "    And hca.account_number = mail.customer_number "+
                        "    And apps.cux_om_undone_ord_dtl_pkg.get_Org_Name(ool.attribute15) like '%"+FACTORY+"%'"+
                        "    And Not Exists (Select 1 "+
                        "           From wsh_delivery_details wdd "+
                        "          Where wdd.source_code = 'OE' "+
                        "            And wdd.released_status = 'S' "+
                        "            And wdd.source_line_id = ool.line_id) "+
                        "    And Not Exists (Select 1 "+
                        "           From cux_oe_bzxx_rsv_result r "+
                        "          Where r.packing_id = brr.packing_id "+
                        "            And r.org_id = brr.org_id "+
                        "            And r.line_id < brr.line_id "+
                        "            And apps.CUX_CUXOESHIPPED_PKG.compare_order_line_number(r.line_id, "+
                        "                                                                    brr.line_id) = 'Y'  "+
                        "            AND r.mix_flag = 'Y') "+
                        "    And apps.CUX_CUXOESHIPPED_PKG.get_comparison_flag(ool.line_id, "+
                        "                                                      ool.ship_from_org_id, "+
                        "                                                      TO_DATE('"+P_DATE+" 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), "+
                        "                                                      TO_DATE('"+P_DATE+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS')) = 'Y' "+
                        "    And Exists "+
                        "  (Select 1 "+
                        "           From inv.MTL_MATERIAL_TRANSACTIONS mmt "+
                        "          Where 1 = 1 "+
                        "            And mmt.organization_id = ool.ship_from_org_id "+
                        "            And mmt.transaction_type_id = 52  "+
                        "            And mmt.trx_source_line_id = ool.line_id "+
                        "            And mmt.transaction_quantity > 0 "+
                        "            And mmt.transaction_date >= "+
                        "                NVL(TO_DATE('"+P_DATE+" 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), "+
                        "                    Sysdate) "+
                        "            And mmt.transaction_date <= "+
                        "                NVL(TO_DATE('"+P_DATE+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), "+
                        "                    Sysdate)) ";
        System.out.println(sql);

    }
}
