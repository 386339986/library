import React, { Component } from 'react'
import { View } from '@tarojs/components'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import './index.less'
import Top from "./components/top/top";
import FuncGrid from "./components/func/func";
import List from "./components/list/list";

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
        <List />
      </View>
    )
  }
}
