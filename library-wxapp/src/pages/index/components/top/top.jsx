import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import {View, Text, Swiper, SwiperItem, Image} from '@tarojs/components'
import { AtButton } from 'taro-ui'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import './top.less'
import userGIF from '../../../../assets/img/user.gif'
import {connect} from "react-redux";

@connect(({ userInfo }) => ({
  userInfo
}))

export default class Top extends Component {

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='top'>
        <View className='top-bar'>
          <View className='left'>您好：{this.props.userInfo.student_name}</View>
          <View className='right'>{this.props.userInfo.student_school}</View>
        </View>
        <View>
          <Image src={userGIF} className='top-gif'/>
        </View>
        {/*<Swiper*/}
        {/*  indicatorColor='#999'*/}
        {/*  indicatorActiveColor='#333'*/}
        {/*  circular*/}
        {/*  indicatorDots*/}
        {/*  autoplay*/}
        {/*  interval='3000'*/}
        {/*>*/}
        {/*  <SwiperItem>*/}
        {/*    <View>1</View>*/}
        {/*  </SwiperItem>*/}
        {/*  <SwiperItem>*/}
        {/*    <View>2</View>*/}
        {/*  </SwiperItem>*/}
        {/*  <SwiperItem>*/}
        {/*    <View>3</View>*/}
        {/*  </SwiperItem>*/}
        {/*</Swiper>*/}
      </View>
    )
  }
}
