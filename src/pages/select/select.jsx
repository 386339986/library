import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import { View, Text, Swiper, SwiperItem } from '@tarojs/components'
import { AtButton } from 'taro-ui'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import './select.less'
import Top from "./components/top";
import FuncGrid from "./components/func";
import Choose from "./components/choose";

export default class Select extends Component {

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='select'>
        <Top />
        <FuncGrid />
        <Choose />
      </View>
    )
  }
}
