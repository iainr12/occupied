import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';
import myData from './data.json';
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn,
} from 'material-ui/Table';
import Checkbox from 'material-ui/Checkbox';
import Lock from 'material-ui/svg-icons/action/lock';
import LockOpen from 'material-ui/svg-icons/action/lock-open';
import {cyan500, pink500, grey500} from 'material-ui/styles/colors';

class Dashboard extends Component {

  constructor() {
    super();

    const tableHeaders = ['MALE East', 'FEMALE East','Accessible', 'FEMALE West', 'MALE West'];

    this.state = {
      data: myData,
      tableHeaders: tableHeaders,
      requiredData: []
    }

    this.getData = this.getData.bind(this);
  }

  processData() {
    const data = this.state.data;
    const requiredData = [];
    for(let i=0;i<=6;i++){
      requiredData[i] = {
        floor: i
      };
      for(let x=0;x<data.length;x++){
        let currentItem = data[x];
        if(currentItem.floor === i.toString()){
          if(currentItem.zone === 'EAST' && currentItem.gender === 'MALE'){
            requiredData[i].eastMale = currentItem.sensors;
          }
          if(currentItem.zone === 'WEST' && currentItem.gender === 'MALE'){
            requiredData[i].westMale = currentItem.sensors;
          }
          if(currentItem.zone === 'EAST' && currentItem.gender === 'FEMALE'){
            requiredData[i].eastFemale = currentItem.sensors;
          }
          if(currentItem.zone === 'WEST' && currentItem.gender === 'FEMALE'){
            requiredData[i].westFemale = currentItem.sensors;
          }
        }
      }
    }
    this.setState({requiredData: requiredData});
  }

  getData() {
    return axios
      .get('http://172.20.10.3:8080/location')
      .then((result) => {
        // console.log(result.data);
        this.setState({data: result.data});
      })
      .then(() => {
        this.processData();
      });
  }

  componentDidMount() {
    this.getData();
    this.dataInterval = setInterval(this.getData, 250);
  }

  render() {
    return (
      <div>
        <Table>
          <TableHeader displaySelectAll={false} adjustForCheckbox={false}>
            <TableRow>
            <TableHeaderColumn style={{'text-align':'center', 'width':'20px'}} key={'floor'}>Floor</TableHeaderColumn>)}
              {this.state.tableHeaders.map(item => 
              <TableHeaderColumn style={{'text-align':'center'}} key={item}>{item}</TableHeaderColumn>)}
            </TableRow>
          </TableHeader>
          <TableBody displayRowCheckbox={false}>
            {this.state.requiredData.map(item =>
              <TableRow key={'tableRow-'+item.floor}>
                <TableRowColumn style={{'text-align':'center', 'width':'20px'}} key={item.floor + item.floor}>{item.floor}</TableRowColumn>
                <TableRowColumn style={{'text-align':'center'}} key={item.floor + '-eastMale'}>
                <div style={{ display: 'inline-flex'}}>
                  <Checkbox style={{width: '20px'}}
                      checkedIcon={<Lock />}
                      uncheckedIcon={<LockOpen/>}
                      checked={item.eastMale[0].occupiedState==='closed'}
                      iconStyle={{fill: cyan500}}
                  />
                  <Checkbox style={{width: '20px'}}
                      checkedIcon={<Lock />}
                      uncheckedIcon={<LockOpen/>}
                      checked={item.eastMale[1].occupiedState==='closed'}
                      iconStyle={{fill: cyan500}}
                  />
                  <Checkbox style={{width: '20px'}}
                      checkedIcon={<Lock />}
                      uncheckedIcon={<LockOpen/>}
                      checked={item.eastMale[2].occupiedState==='closed'}
                      iconStyle={{fill: cyan500}}
                  />
                </div>
                </TableRowColumn>
                <TableRowColumn style={{'text-align':'center'}} key={item.floor + '-eastFemale'}>
                  <div style={{ display: 'inline-flex'}}>
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<Lock />}
                        uncheckedIcon={<LockOpen/>}
                        checked={item.eastFemale[0].occupiedState==='closed'}
                        iconStyle={{fill: pink500}}
                    />
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<Lock />}
                        uncheckedIcon={<LockOpen/>}
                        checked={item.eastFemale[1].occupiedState==='closed'}
                        iconStyle={{fill: pink500}}
                    />
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<Lock />}
                        uncheckedIcon={<LockOpen/>}
                        checked={item.eastFemale[2].occupiedState==='closed'}
                        iconStyle={{fill: pink500}}
                    />
                  </div>
                </TableRowColumn>
                <TableRowColumn style={{'text-align':'center'}} key={item.floor + '-accessible'}>
                  <div style={{ display: 'inline-flex'}}>
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<Lock />}
                        uncheckedIcon={<LockOpen/>}
                        checked={false}
                        iconStyle={{fill: grey500}}
                    />
                  </div>
                </TableRowColumn>
                <TableRowColumn style={{'text-align':'center'}} key={item.floor + '-westFemale'}>
                  <div style={{ display: 'inline-flex'}}>
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<Lock />}
                        uncheckedIcon={<LockOpen/>}
                        checked={item.westFemale[0].occupiedState==='closed'}
                        iconStyle={{fill: pink500}}
                    />
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<Lock />}
                        uncheckedIcon={<LockOpen/>}
                        checked={item.westFemale[1].occupiedState==='closed'}
                        iconStyle={{fill: pink500}}
                    />
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<Lock />}
                        uncheckedIcon={<LockOpen/>}
                        checked={item.westFemale[2].occupiedState==='closed'}
                        iconStyle={{fill: pink500}}
                    />
                  </div>
                </TableRowColumn>
                <TableRowColumn style={{'text-align':'center'}} key={item.floor + '-westMale'}>
                  <div style={{ display: 'inline-flex'}}>
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<Lock />}
                        uncheckedIcon={<LockOpen/>}
                        checked={item.westMale[0].occupiedState==='closed'}
                        iconStyle={{fill: cyan500}}
                    />
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<Lock />}
                        uncheckedIcon={<LockOpen/>}
                        checked={item.westMale[1].occupiedState==='closed'}
                        iconStyle={{fill: cyan500}}
                    />
                    <Checkbox style={{width: '20px'}}
                        checkedIcon={<Lock />}
                        uncheckedIcon={<LockOpen/>}
                        checked={item.westMale[2].occupiedState==='closed'}
                        iconStyle={{fill: cyan500}}
                    />
                  </div>
                </TableRowColumn>
              </TableRow>)}
          </TableBody>
        </Table>
      </div>
    );
  }
}

export default Dashboard;
