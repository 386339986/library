import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import {View, Text, Swiper, SwiperItem} from '@tarojs/components'
import { AtButton } from 'taro-ui'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import './top.less'

export default class Top extends Component {

  constructor(props) {
    super(props);
    this.state = {
      userName: '测试用户',
      school: '测试学校',
    }
  }

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='top'>
        <View className='top-bar'>
          <View className='left'>您好：{this.state.userName}</View>
          <View className='right'>{this.state.school}</View>
        </View>
        <Swiper
          indicatorColor='#999'
          indicatorActiveColor='#333'
          circular
          indicatorDots
          autoplay
          interval='3000'
        >
          <SwiperItem>
            <View>1</View>
          </SwiperItem>
          <SwiperItem>
            <View>2</View>
          </SwiperItem>
          <SwiperItem>
            <View>3</View>
          </SwiperItem>
        </Swiper>
      </View>
    )
  }
}
