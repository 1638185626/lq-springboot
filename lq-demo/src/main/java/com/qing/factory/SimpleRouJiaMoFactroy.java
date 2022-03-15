package com.qing.factory;

/**
 * @className: SimpleRouJiaMoFactroy
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/15
 **/
public class SimpleRouJiaMoFactroy {
    public RouJiaMo createRouJiaMo(String type)
    {
        RouJiaMo rouJiaMo = null;
        if (type.equals("Suan"))
        {
            rouJiaMo = new SuanRouJiaMo();

        } else if (type.equals("Tian"))
        {
            rouJiaMo = new TianRouJiaMo();
        } else if (type.equals("La"))
        {
            rouJiaMo = new LaRouJiaMo();
        }
        return rouJiaMo;
    }

}
