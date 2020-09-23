import React, { Component } from 'react'
import { View, Text } from '@tarojs/components'
import { AtCard } from 'taro-ui'

import "taro-ui/dist/style/components/card.scss" // 按需引入
import './rule.less'

export default class Rule extends Component {

  constructor() {
    super(...arguments);
    this.state = {
      schoolName: '天津大学',
      status: '正常使用中',
      statusStyle: {color: 'green'}
    }
  }

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='rule'>
        <View className='title'>
          <Text className>{this.state.schoolName} 使用规则</Text>
        </View>

        <AtCard
          title='当前状态'
          extra={this.state.status}
          isFull
          extraStyle={this.state.statusStyle}
        >
          <Text>如果您的状态为异常，请联系馆内工作人员</Text>
        </AtCard>
        <AtCard
          title='用馆规则'
          isFull
        >
          <Text>来馆选座、到馆签到、离馆退座，临时离开请点击暂离。</Text>
          <Text>系统开闭时间见对应馆室名下方小字。</Text>
        </AtCard>
        <AtCard
          title='预约规则'
          isFull
        >
          <Text>开馆前两小时可预约当天座位，预约第二天座位需要使用道具</Text>
        </AtCard>
        <AtCard
          title='违规规则'
          isFull
        >
          <Text>预约后规定时间内未到馆签到记录违规；</Text>
          <Text>被监督占座不签到记录违规；</Text>
          <Text>离馆不退座记录违规；</Text>
          <Text>“清除违规记录”道具使用后只能清除最近一次违规记录。</Text>
        </AtCard>
        <AtCard
          title='黑名单规则'
          isFull
        >
          <Text>1. 15天内发生3次违规进入黑名单，黑名单禁用系统7天。</Text>
          <Text>2. “黑名单解除”道具使用后立即移除黑名单，进入黑名单后清除违规记录不会移除黑名单。</Text>
          <Text>3. 违规使用监督占座功能的（如照片严重失实），查证后立即进入长期黑名单且不可使用道具移除。</Text>
          <Text>4. 已开通学号验证的学校,如果绑定他人学号或者借学号给他人绑定,查证后立即进入长期黑名单且不可使用道具移除。</Text>
        </AtCard>
      </View>
    )
  }
}
