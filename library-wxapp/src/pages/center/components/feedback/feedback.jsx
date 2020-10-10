import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import { View, Text } from '@tarojs/components'
import {AtButton, AtTextarea } from 'taro-ui'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import "taro-ui/dist/style/components/textarea.scss" // 按需引入
import './feedback.less'

export default class Feedback extends Component {

  constructor(props) {
    super(props);
    this.state = {
      value: ''
    }
  }

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  handleChange (value) {
    this.setState({
      'value' : value
    })
  }

  render () {
    return (
      <View className='feedback'>
        <View className='title'>
          <Text>使用说明:</Text>
        </View>
        <View className='content'>
          <Text>该功能直接向图书馆管理员留言，感谢你的热心建议。管理员会在工作时间尽快回复。</Text>
        </View>

        <AtTextarea
          value={this.state.value}
          onChange={this.handleChange.bind(this)}
          maxLength={3000}
          placeholder='想对我们说什么，就大胆说吧'
        />
        <View className='button'>
          <AtButton
            type='primary'
            size='small'
            circle
          >提交反馈</AtButton>
          <AtButton
            type='secondary'
            size='small'
            circle
            onClick={() => (this.props.onWinChange('feedback', false))}
          >返回</AtButton>
        </View>
      </View>
    )
  }
}
