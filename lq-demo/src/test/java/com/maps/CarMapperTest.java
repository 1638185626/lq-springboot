package com.maps;

import com.utils.Assert;
import org.junit.Test;




/**
 * @className: CarMapperTest
 * @description: TODO 类描述
 * @author: qing liu
 * @date: 2022/3/20
 **/
public class CarMapperTest {

    @Test
    public void shouldMapCarToDto() {
        //given
        Car car = new Car( "Morris", 5, CarType.SEDAN );

        //when
        CarDto carDto = CarMapper.INSTANCE.carToCarDto( car );
        System.out.println(carDto);

    }


}
