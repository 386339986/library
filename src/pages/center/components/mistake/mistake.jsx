import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import { View, Text } from '@tarojs/components'
import { AtButton } from 'taro-ui'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import './mistake.less'

export default class Mistake extends Component {

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='mistake'>
        <AtButton
          type='secondary'
          size='small'
          circle
          onClick={() => (this.props.onWinChange('mistake', false))}
        >返回</AtButton>
      </View>
    )
  }
}
