import React, { Component } from 'react'
import Taro from '@tarojs/taro';
import {View} from '@tarojs/components'
import { connect } from "react-redux";

import "taro-ui/dist/style/components/button.scss" // 按需引入
import "taro-ui/dist/style/components/tabs.scss"
import './index.less'
import Top from "./components/top/top";
import FuncGrid from "./components/func/func";
import Seat from "./components/seat/seat";
import {setName, setNumber, setPhone, setSchool, setSchoolId, setToken, setUserId} from "../../actions/info";
import {getRoomList_servers, getUser_servers, login_servers} from "../../servers/servers";
import {HTTP_STATUS} from "../../servers/config";
import {AtList, AtListItem, AtTabs, AtTabsPane} from "taro-ui";

@connect(({ localUserInfo }) => ({
  localUserInfo
}), (dispatch) => ({
  setName (name) {
    dispatch(setName(name))
  },
  setNumber (number) {
    dispatch(setNumber(number))
  },
  setPhone (phone) {
    dispatch(setPhone(phone))
  },
  setToken (token) {
    dispatch(setToken(token))
  },
  setUserId (id) {
    dispatch(setUserId(id))
  },
  setSchool (school) {
    dispatch(setSchool(school))
  },
  setSchoolId (id) {
    dispatch(setSchoolId(id))
  }
}))

export default class Select extends Component {

  constructor() {
    super(...arguments);
    this.state = {
      tabList: [],
      current: 0,
      roomList: [],
      show: false
    }
  }

  componentWillMount () {
  }

  componentDidMount () {
    Taro.showLoading({
      title: '加载中...',
    })
    let token = Taro.getStorageSync('Token')
    if (token === null || token === '' || token === undefined) {
      Taro.hideLoading()
      console.log('未登录')
      Taro.redirectTo({
        url: '/pages/register/register'
      })
    } else {
      let flag = this.props.localUserInfo.token
      if (flag === null || flag === '') {
        getUser_servers().then(res => {
          // console.log(res)
          if (res.code === HTTP_STATUS.SUCCESS) {
            console.log(res.data)
            let { id, schoolId, schoolName, userName, phone, idNumber } = res.data
            this.props.setUserId(id)
            this.props.setName(userName)
            this.props.setNumber(idNumber)
            this.props.setSchoolId(schoolId)
            this.props.setSchool(schoolName)
            this.props.setPhone(phone)
            this.props.setToken(token)
            this.setState({
              show: true
            })
            this.getRoomList(this.props.localUserInfo.school_id)
          } else {
            Taro.atMessage({
              'message': '获取信息错误',
              'type': 'warning',
            });
          }
        }).catch(err => {
          console.log(err)
          Taro.atMessage({
            'message': '发生错误',
            'type': 'error',
          });
        })
      }
      setTimeout(function () {
        Taro.hideLoading()
      }, 1000)
    }
  }

  changeCurrent (value) {
    this.setState({
      current: value
    })
  }

  getRoomList(school_id) {
    // console.log('getRoomList')
    getRoomList_servers(school_id).then(res => {
      console.log(res)
      let rooms = JSON.parse(res.data)
      let tabList = []
      let roomList = []
      for (let i in rooms) {
        let flag = false
        for (let j in tabList) {
          if (tabList[j]['title'] === rooms[i].campus) {
            flag = true
          }
        }
        if (flag === false) {
          tabList.push({title: rooms[i].campus})
          let o = {}
          o[rooms[i].campus] = []
          roomList.push(o)
        }

        for (let j in roomList) {
          if (roomList[j].hasOwnProperty(rooms[i].campus)) {
            let o = {}
            o['room'] = rooms[i].name
            o['times'] = [['8:00', '22:00']]
            o['advance'] = '2.0'
            o['free'] = rooms[i].available
            o['total'] = rooms[i].count
            o['id'] = rooms[i].id
            roomList[j][rooms[i].campus].push(o)
            break;
          }
        }
      }
      this.setState({
        tabList: tabList,
        roomList: roomList
      })
    })
  }

  componentWillUnmount () {
  }

  componentDidShow () {
    if (this.props.localUserInfo.token !== '') {
      this.getRoomList(this.props.localUserInfo.school_id)
      this.setState({
        show: true
      })
    }
  }

  componentDidHide () {
    this.setState({
      show: false
    })
  }

  render () {
    return (
      <View className='select'>
        <Top />
        <FuncGrid />
        {this.state.show ? <Seat /> : null }
        {/*<List tabList={this.state.tabList} current={this.state.current} roomList={this.state.roomList} />*/}
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
                            onClick={() => (Taro.navigateTo({url: '/pages/select/select?id=' + roomItem['id']}))}
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
      </View>
    )
  }
}
