package com.qing.factory;

/**
 * @className: XianRouJiaMoStore
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/15
 **/
public class XianRouJiaMoStore extends RoujiaMoStore{
    @Override
    public RouJiaMo createRouJiaMo(String type)
    {
        RouJiaMo rouJiaMo = null;
//        if (type.equals("Suan"))
//        {
//            rouJiaMo = new XianSuanRouJiaMo();
//
//        } else if (type.equals("Tian"))
//        {
//            rouJiaMo = new XianTianRouJiaMo();
//        } else if (type.equals("La"))
//        {
//            rouJiaMo = new XianLaRouJiaMo();
//        }
        return rouJiaMo;

    }

}
