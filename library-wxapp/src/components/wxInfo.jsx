import React, { Component } from "react";
import Taro from '@tarojs/taro';
import {Image, OpenData, Text, View} from "@tarojs/components";
import './wxInfo.less';
import noPicJpg from '../assets/img/no_pic.jpg';

export default class WxInfo extends Component {

  constructor() {
    super(...arguments);
    this.state = {
      wxApp: false,
    };
  }

  componentDidMount() {
    if (Taro.getEnv() === Taro.ENV_TYPE.WEAPP) {
      this.setState({
        wxApp: true
      })
    }
  }

  render() {
    let renderInfo;
    if (Taro.getEnv() === Taro.ENV_TYPE.WEAPP) {
      renderInfo = (
        <View className='info-container'>
          <View className='user-avatar'><OpenData type='userAvatarUrl' /></View>
          <View className='user-wxName'><OpenData type='userNickName' defaultAvatar={noPicJpg}/></View>
        </View>
      )
    } else {
      renderInfo = (
      <View className='info-container'>
        <Image src={noPicJpg} className='user-avatar'></Image>
        <Text className='user-name'>H5页面</Text>
      </View>
      )
    }
    return (
      <View className='user-info'>
        <Text className='user-tips'>
          {/*{this.state.tips}*/}
        </Text>
        {/*<Image src={noPicJpg} className='user-logo' />*/}
        {renderInfo}
      </View>
    )
  }

}
