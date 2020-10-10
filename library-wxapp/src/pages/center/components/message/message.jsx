import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import { View, Text, ScrollView } from '@tarojs/components'
import {AtButton, AtForm} from 'taro-ui'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import './message.less'

export default class Message extends Component {

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  onScrollToUpper() {}

  // or 使用箭头函数
  // onScrollToUpper = () => {}

  onScroll(e){
    console.log(e.detail)
  }

  render () {
    const scrollStyle = {
      height: '400px'
    }
    const scrollTop = 0
    const Threshold = 20
    const vStyleA = {
      height: '150px',
      'background-color': 'rgb(26, 173, 25)'
    }
    const vStyleB = {
      height: '150px',
      'background-color': 'rgb(39,130,215)'
    }
    const vStyleC = {
      height: '150px',
      'background-color': 'rgb(241,241,241)',
      color: '#333'
    }

    return (
      <View className='message'>
        <ScrollView
          className='scrollview'
          scrollY
          scrollWithAnimation
          scrollTop={scrollTop}
          style={scrollStyle}
          lowerThreshold={Threshold}
          upperThreshold={Threshold}
          onScrollToUpper={this.onScrollToUpper.bind(this)} // 使用箭头函数的时候 可以这样写 `onScrollToUpper={this.onScrollToUpper}`
          onScroll={this.onScroll}
        >
          <Text>您没有消息记录</Text>
        </ScrollView>
        <AtButton
          type='secondary'
          size='small'
          circle
          onClick={() => (this.props.onWinChange('message', false))}
        >返回</AtButton>

      </View>
    )
  }
}
