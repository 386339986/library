import React, { Component } from "react";
import Taro from '@tarojs/taro';
import {Image, Text, View} from "@tarojs/components";
import './wxInfo.less';
import noPicJpg from '../assets/img/no_pic.jpg';

export default class WxInfo extends Component {

  constructor() {
    super(...arguments);
    this.state = {
      tips: '',
    };
  }

  componentDidMount() {
    if (Taro.getEnv() !== Taro.ENV_TYPE.WEAPP) {
      this.setState({
        tips: '当前运行环境非微信小程序！'
      })
    }
  }

  render() {
    return (
      <View className='user-info'>
        <Text className='user-tips'>
          {/*{this.state.tips}*/}
        </Text>
        <Image src={noPicJpg} className='user-logo' />
        <Text className='user-name'>测试用户</Text>
      </View>
    )
  }

}
