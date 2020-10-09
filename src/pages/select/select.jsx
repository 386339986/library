import React, { Component } from 'react';
import Taro from '@tarojs/taro';
import {View, Text, Image} from '@tarojs/components';
import { AtButton } from 'taro-ui';

import "taro-ui/dist/style/components/button.scss";
import "taro-ui/dist/style/components/flex.scss" ;
import './select.less';
import Grid_3 from '../../assets/img/grid_1.png';
import Grid_Free from '../../assets/img/grid_3.png';
import Grid_Use from '../../assets/img/grid_4.png';
import Grid_Active from '../../assets/img/grid_5.png';
import Container from "./components/container";

export default class Select extends Component {

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='select'>
        <View className='at-row at-row__justify--around footer'>
          <View className='at-col at-col-5'>
            <AtButton
              type='secondary'
              size='small'
              circle
              onClick={() => (Taro.navigateBack())}
            >返回</AtButton>
          </View>
          <View className='at-col at-col-5'>
            <AtButton
              type='primary'
              size='small'
              circle
              onClick={() => (Taro.navigateBack())}
            >选座</AtButton>
          </View>
        </View>
        <View className='top'>
          <View className='header-panel at-row'>
            <View className='icon_container at-col at-col-3'>
              <Image src={Grid_3} className='icon'></Image>
              <View className='font'>
                <Text>出入口</Text>
              </View>
            </View>
            <View className='icon_container at-col at-col-3'>
              <Image src={Grid_Use} className='icon'></Image>
              <View className='font'>
                <Text>已选座位</Text>
              </View>
            </View>
            <View className='icon_container at-col at-col-3'>
              <Image src={Grid_Active} className='icon'></Image>
              <View className='font'>
                <Text>我的座位</Text>
              </View>
            </View>
            <View className='icon_container at-col at-col-3'>
              <Image src={Grid_Free} className='icon'></Image>
              <View className='font'>
                <Text>可选座位</Text>
              </View>
            </View>
          </View>
          <View className='title'>
            <Text>请选择座位</Text>
          </View>
        </View>
        <Container />
      </View>
    )
  }
}
