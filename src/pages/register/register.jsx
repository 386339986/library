import React, { Component } from 'react'
import { View, Text } from '@tarojs/components'
import { AtButton } from 'taro-ui'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import './register.less'
import WxInfo from "./components/wxInfo";
import Body from "./components/body";

export default class Register extends Component {

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='index'>
        <View className='register_head'>
          <Text className='title'>图书馆选座预约系统</Text>
          <WxInfo />
        </View>
        <Body />

        {/*<Text>Hello world!</Text>*/}
        {/*<AtButton type='primary'>I need Taro UI</AtButton>*/}
        {/*<Text>Taro UI 支持 Vue 了吗？</Text>*/}
        {/*<AtButton type='primary' circle={true}>支持</AtButton>*/}
        {/*<Text>共建？</Text>*/}
        {/*<AtButton type='secondary' circle={true}>来</AtButton>*/}
      </View>
    )
  }
}
