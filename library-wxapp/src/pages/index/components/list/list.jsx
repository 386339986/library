import React, { Component } from 'react'
import Taro from '@tarojs/taro'
import { View, Text } from '@tarojs/components'
import { AtList, AtListItem, AtTabs, AtTabsPane } from 'taro-ui'

import "taro-ui/dist/style/components/button.scss" // 按需引入
import "taro-ui/dist/style/components/tabs.scss" // 按需引入
import "taro-ui/dist/style/components/list.scss" // 按需引入
import './list.less'

export default class List extends Component {

  constructor() {
    super(...arguments);
    this.state = {
      tabList: [{title: '卫津路校区'}, {title: '北洋园校区'}],
      current: 0,
      roomList: [
        {'卫津路校区': [{room: '101', times: [['8:00', '22:00']], advance: '2.0', free: 8, total: 18},
            {room: '102', times: [['8:00', '22:00']], advance: '2.0', free: 16, total: 20},
            {room: '103', times: [['8:00', '22:00']], advance: '2.0', free: 12, total: 20},
            {room: '202', times: [['8:00', '20:00']], advance: '1.0', free: 12, total: 180},
            {room: '256', times: [['8:00', '22:00']], advance: '2.0', free: 16, total: 450},
            {room: '345', times: [['8:00', '22:00']], advance: '2.0', free: 320, total: 420}
            ]},
        {'北洋园校区': [{room: '101', times: [['8:00', '22:00']], advance: '2.0', free: 8, total: 18},
            {room: '102', times: [['8:00', '22:00']], advance: '2.0', free: 16, total: 20},
            {room: '103', times: [['8:00', '22:00']], advance: '2.0', free: 12, total: 20}
         ]}]
    }
  }

  changeCurrent (value) {
    this.setState({
      current: value
    })
  }

  componentWillMount () { }

  componentDidMount () { }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='choose'>
        <AtTabs
          current={this.state.current}
          tabList={this.state.tabList}
          onClick={this.changeCurrent.bind(this)}
          scroll
        >
          {
            Object.keys(this.state.tabList).map( (item, index) => (
              <AtTabsPane current={this.state.current} index={index} key={index} >
                <AtList>
                  {
                    Object.values(this.state.roomList[item]).map( (roomsItem, roomsIndex) => (
                      roomsItem.map( (roomItem, roomIndex) => (
                        <AtListItem
                          title={this.state.tabList[index]['title'] + ' ' + roomItem['room'] + '室'}
                          note={roomItem['times'][0][0] + ' ~ ' + roomItem['times'][0][1] + ' 开馆前可提前 ' +
                            roomItem['advance'] + '小时预定'}
                          extraText={roomItem['free'] + '/' + roomItem['total']}
                          key={roomIndex}
                          onClick={() => (Taro.navigateTo({url: '/pages/select/select'}))}
                        >
                        </AtListItem>
                      ))
                    ))
                  }
                </AtList>
              </AtTabsPane>
            ))
          }
        </AtTabs>
      </View>
    )
  }
}
