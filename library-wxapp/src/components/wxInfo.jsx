import React, { Component } from "react";
import Taro from '@tarojs/taro';
import {Image, OpenData, Text, View} from "@tarojs/components";
import './wxInfo.less';
import noPicJpg from '../assets/img/no_pic.jpg';
import {connect} from "react-redux";

@connect(({userInfo}) => ({
  userInfo
}))

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
      if (this.props.userInfo.student_id !== 0) {
        renderInfo = (
          <View className='info-container'>
            <View className='user-avatar'><OpenData type='userAvatarUrl' defaultAvatar={noPicJpg}/></View>
            <Text className='user-name'>{this.props.userInfo.student_name}</Text>
          </View>
        )
      } else {
        renderInfo = (
          <View className='info-container'>
            <View className='user-avatar'><OpenData type='userAvatarUrl' defaultAvatar={noPicJpg}/></View>
            <View className='user-wxName'><OpenData type='userNickName' /></View>
          </View>
        )
      }
    } else {
      renderInfo = (
      <View className='info-container'>
        <Image src={noPicJpg} className='user-avatar'></Image>
        <Text className='user-name'>{this.props.userInfo.student_name}</Text>
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
