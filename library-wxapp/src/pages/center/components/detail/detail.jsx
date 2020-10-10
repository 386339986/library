import React, { Component } from 'react'
import { View } from '@tarojs/components'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import './detail.less'

export default class Detail extends Component {

  constructor(props) {
    super(props);
    this.state = {
      show: this.props.show
    }
  }

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='study-detail'>
        <View className='table-cell'>
          10小时
          <View className='info'>
            学习时间
          </View>
        </View>
        <View className='table-cell'>
          10000名
          <View className='info'>
            排名
          </View>
        </View>
        <View className='table-cell'>
          3小时
          <View className='info'>
            单日最长学习时间
          </View>
        </View>
        {/*<View className='bg' />*/}
      </View>
    )
  }
}
