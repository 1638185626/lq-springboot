package com.maps;

import com.utils.Assert;
import org.junit.Test;

import javax.sound.midi.Sequencer;


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


    @Test
    public void mapCarToDto() {

        byte[] ads = new byte[]{53, 55, 50, 74, 56, 89, 51, 90, 81, 54, 57, 53, 54, 55, 49, 55};
        String string = new String(ads);
        System.out.println(string);
    }


}
