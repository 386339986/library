import React, { Component } from 'react'
import Taro, {getCurrentInstance} from '@tarojs/taro'
import {View, Text, Image} from '@tarojs/components'
import { AtButton, AtMessage, AtModal } from 'taro-ui'
import AnyTouch from 'any-touch'

import "taro-ui/dist/style/components/button.scss"
import "taro-ui/dist/style/components/flex.scss"
import "taro-ui/dist/style/components/message.scss"
import "taro-ui/dist/style/components/modal.scss"
import './select.less'
import Grid_3 from '../../assets/img/grid_1.png'
import Grid_Free from '../../assets/img/grid_3.png'
import Grid_Use from '../../assets/img/grid_4.png'
import Grid_Active from '../../assets/img/grid_5.png'
import { getRoomInfo_servers, postSelectSeat_servers } from "../../servers/servers"
import { connect } from "react-redux"
import { HTTP_STATUS } from "../../servers/config"
// import Container from "./components/container";

@connect(({ userInfo }) => ({
  userInfo
}))
export default class Select extends Component {

  constructor() {
    super(...arguments);
    this.state = {
      gridCellSize: 35,
      gridSize: {},
      grid: [],
      seatNumber: {},
      scale: 1,
      roomId: 0,
      top: '0PX',
      left: '0PX',
      selected: false,
      selectNumber: [],
      modelOpen: false
    }
    this.at = new AnyTouch ()
  }

  componentWillMount () {
    const params = getCurrentInstance().router.params
    if (params.hasOwnProperty('id')) {
      console.log("select has id")
      this.setState({
        roomId: params['id']
      })
    } else {
      Taro.atMessage({
        'message': '参数错误，即将自动返回',
        'type': 'error',
      });
      setTimeout(Taro.navigateBack(), 2000)
      // Taro.navigateBack()
    }
    this.at.set({domEvents: false})
    this.at.on('pinch', (ev) => this.onPinch(ev))
    this.at.on('pan', (ev) => this.onPanMove(ev))
  }

  refreshRoomList() {
    getRoomInfo_servers(this.state.roomId).then(res => {
      let data = JSON.parse(res.data)
      let seatInfo = JSON.parse(data.seats)
      let roomSize = JSON.parse(seatInfo['roomSize'])
      let seatNumber = JSON.parse(seatInfo['seatNumber'])
      this.setState({
        gridSize: roomSize,
        grid: seatInfo.seats,
        seatNumber: seatNumber
      })
    })
  }

  componentDidMount () {
  }

  componentWillUnmount () { }

  componentDidShow () { this.refreshRoomList() }

  componentDidHide () { }

  onPanMove(ev) {
    this.setState({
      top: parseInt(this.state.top) + ev.deltaY + 'PX',
      left: parseInt(this.state.left) + ev.deltaX + 'PX'
    })
  }

  onPinch(ev) {
    this.setState({
      scale: Math.round(this.state.scale * ev.deltaScale * 100) / 100
    })
  }

  getSeatNumber(item, rowIndex, colIndex) {
    let number = '';
    switch (item) {
      case 3:
      case 4:
      case 5:
        number = this.state.seatNumber['r' + rowIndex]['c' + colIndex];
        break;
      default:
        break;
    }
    return number
  }

  selectSeat(row, col) {
    if (!this.state.selected) {
      this.setState({
        selectNumber: [row, col],
        selected: true
      })
    } else {
      if (this.state.selectNumber[0] === row && this.state.selectNumber[1] === col ) {
        this.setState({
          selectNumber: [],
          selected: false
        })
      }
    }
    // console.log("selectSeat " + row + " " + col)
  }

  confirmSeat() {
    postSelectSeat_servers(this.state.roomId, this.state.selectNumber).then(res => {
      console.log(res)
      //let resJson = JSON.parse(res)
      if (res.code === HTTP_STATUS.CLIENT_ERROR) {
        Taro.atMessage({
          'message': res.msg,
          'type': "error"
        })
      } else if (res.code === HTTP_STATUS.SUCCESS) {
        this.setState({
          modelOpen: true
        })
      }
    }).catch(err => {
      console.log(err)
    })
    console.log("confirm")
  }

  createGrid (item, row, col) {

    return (
      item > 0 ?
        <View
          className={'grid-cell grid-' +
          (item === 3 ? (this.state.selectNumber[0] === row && this.state.selectNumber[1] === col ? 5 : 3) : item)}
          key={row + ',' + col}
          style={'top:' + (this.state.gridCellSize * row + 10) + 'PX;left:'
          + this.state.gridCellSize * col + 'PX'}
          onClick={item === 3 ? this.selectSeat.bind(this, row, col) : null}
        >
          <View
            className='img'
            hoverClass='none'
          >
            {this.getSeatNumber(item, row, col)}
          </View>
        </View>
        :
        null
    )
  }

  backHome() {
    Taro.switchTab({
      url: '/pages/index/index'
    })
  }

  render () {
    return (
      <View className='select'>
        <AtModal
          isOpened={this.state.modelOpen}
          title='预约成功'
          confirmText='确认'
          onConfirm={this.backHome}
          content='座位预约成功，请在15分钟内签到'
        />
        <AtMessage />
        <View className='at-row at-row__justify--around footer'>
          <View className='at-col at-col-5'>
            <AtButton
              type='secondary'
              size='small'
              circle
              onClick={() => (Taro.navigateBack())}
            >返回</AtButton>
          </View>
          <View className='at-col at-col-5'>
            <AtButton
              type='primary'
              size='small'
              circle
              onClick={this.confirmSeat.bind(this)}
            >选座</AtButton>
          </View>
        </View>
        <View className='top'>
          <View className='header-panel at-row'>
            <View className='icon_container at-col at-col-3'>
              <Image src={Grid_3} className='icon'></Image>
              <View className='font'>
                <Text>出入口</Text>
              </View>
            </View>
            <View className='icon_container at-col at-col-3'>
              <Image src={Grid_Use} className='icon'></Image>
              <View className='font'>
                <Text>已选座位</Text>
              </View>
            </View>
            <View className='icon_container at-col at-col-3'>
              <Image src={Grid_Active} className='icon'></Image>
              <View className='font'>
                <Text>我的选座</Text>
              </View>
            </View>
            <View className='icon_container at-col at-col-3'>
              <Image src={Grid_Free} className='icon'></Image>
              <View className='font'>
                <Text>可选座位</Text>
              </View>
            </View>
          </View>
          <View className='title'>
            <Text>请选择座位</Text>
          </View>
        </View>
        {/*<Container data={this.state.id} />*/}
        <View className='container-fluid'>
          <View
            className='wrapper'
            onTouchStart={this.at.catchEvent.bind(this.at)}
            onTouchMove={this.at.catchEvent.bind(this.at)}
            onTouchEnd={this.at.catchEvent.bind(this.at)}
            onTouchCancel={this.at.catchEvent.bind(this.at)}
          >
            <View
              className='content'
              style={'width:' + this.state.gridCellSize * this.state.gridSize['col']
              + 'PX;height:' + this.state.gridCellSize * this.state.gridSize['row'] + 'PX;'
              + 'transform: translate(' + this.state.left + ',' + this.state.top +') scale(' + this.state.scale + ');'}
            >
              {
                this.state.grid.map((rowItem, rowIndex) => (
                  rowItem.map((colItem, colIndex) => (
                    this.createGrid(colItem, rowIndex, colIndex)
                  ))
                ))
              }
            </View>
          </View>
        </View>
      </View>
    )
  }
}
