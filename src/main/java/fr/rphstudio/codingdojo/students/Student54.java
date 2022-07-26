/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.rphstudio.codingdojo.students;

import java.util.*;
import fr.rphstudio.codingdojo.game.Pod;
import fr.rphstudio.codingdojo.game.PodPlugIn;

public class Student54 extends PodPlugIn
{
    boolean isCharging;
    boolean isFar;
    boolean condi= false;
    int[] array= new int[] {237, 17, 28,237, 17, 28,17, 233, 237,17, 237, 65,245, 245, 12,245, 161, 5};
    Random random= new Random();

    public Student54(final Pod pod) {
        super(pod);
        this.isCharging = false;
        this.isFar = false;
    }

    private float __getdist(float sqrt, float n, final float n2, final float n3) {
        sqrt = n2 - sqrt;
        n = n3 - n;
        final float n4 = sqrt;
        final float n5 = n4 * n4;
        final float n6 = n;
        return sqrt = this.sqrt(n5 + n6 * n6);
    }

    private float __getDiffAngle(float n, final float n2) {
        if ((n = n2 - n) < -180.0f) {
            n += 360.0f;
        }
        if (n > 180.0f) {
            n -= 360.0f;
        }
        return n;
    }

    private float __getAbsAngle(float n, float n2, final float n3, final float n4) {
        n = n3 - n;
        n2 = n4 - n2;
        return this.atan2(n2, n);
    }

    private void rgb(){
        float pc=getLapPercent();

        if (pc>95.0f && !condi){
            setPlayerColor(array[random.nextInt(array.length)],array[random.nextInt(array.length)],array[random.nextInt(array.length)],255);
            condi=true;
        }
        else if(pc==0.0f){
            condi=false;
        }
    }

    private int __getNearestChargingCheckPointIndex(final float n, final float n2) {
        float n3 = 1.0E9f;
        int n4 = -1;
        for (int i = 0; i < this.getNbRaceCheckPoints(); ++i) {
            final float _getdist;
            if (this.isCheckPointCharging(i) && (_getdist = this.__getdist(n, n2, this.getCheckPointX(i), this.getCheckPointY(i))) < n3) {
                n4 = i;
                n3 = _getdist;
            }
        }
        return n4;
    }

    public void process(final int n) {
        this.setPlayerName("[Romuald V2.0]");
        this.selectShip(35);
        this.rgb();
        final float shipPositionX = this.getShipPositionX();
        final float shipPositionY = this.getShipPositionY();
        final float shipAngle = this.getShipAngle();
        final int nextCheckPointIndex;
        final int n2 = ((nextCheckPointIndex = this.getNextCheckPointIndex()) + 1) % this.getNbRaceCheckPoints();
        final float checkPointX = this.getCheckPointX(nextCheckPointIndex);
        final float checkPointY = this.getCheckPointY(nextCheckPointIndex);
        final float checkPointX2 = this.getCheckPointX(n2);
        final float checkPointY2 = this.getCheckPointY(n2);
        final float _getAbsAngle = this.__getAbsAngle(shipPositionX, shipPositionY, checkPointX, checkPointY);
        final float _getdist = this.__getdist(shipPositionX, shipPositionY, checkPointX, checkPointY);
        final float _getDiffAngle = this.__getDiffAngle(shipAngle, _getAbsAngle);
        final float _getAbsAngle2 = this.__getAbsAngle(shipPositionX, shipPositionY, checkPointX2, checkPointY2);
        final float _getdist2 = this.__getdist(shipPositionX, shipPositionY, checkPointX2, checkPointY2);
        final float _getDiffAngle2 = this.__getDiffAngle(shipAngle, _getAbsAngle2);
        final float _getDiffAngle3 = this.__getDiffAngle(this.__getAbsAngle(0.0f, 0.0f, this.getShipSpeedX(), this.getShipSpeedY()), _getAbsAngle);
        if (!this.isCharging) {
            if (this.getShipBatteryLevel() <= 13.0f || (this.isCheckPointCharging(nextCheckPointIndex) && this.getShipBatteryLevel() <= 18.0f)) {
                this.isCharging = true;
            }
        }
        else if (this.getShipBatteryLevel() >= 99.0f) {
            this.isCharging = false;
        }
        if (this.isCharging) {
            final int _getNearestChargingCheckPointIndex = this.__getNearestChargingCheckPointIndex(shipPositionX, shipPositionY);
            final float _getDiffAngle4 = this.__getDiffAngle(shipAngle, this.__getAbsAngle(shipPositionX, shipPositionY, this.getCheckPointX(_getNearestChargingCheckPointIndex), this.getCheckPointY(_getNearestChargingCheckPointIndex)));
            final float _getdist3;
            if ((_getdist3 = this.__getdist(shipPositionX, shipPositionY, this.getCheckPointX(_getNearestChargingCheckPointIndex), this.getCheckPointY(_getNearestChargingCheckPointIndex))) <= 4.0f) {
                if (_getdist3 >= 0.5f) {
                    this.turn(_getDiffAngle4);
                    if (this.getShipSpeed() <= 1.0f) {
                        this.accelerateOrBrake(0.66f);
                    }
                    if (this.getShipSpeed() >= 2.0f) {
                        this.accelerateOrBrake(-1.0f);
                    }
                }
                else {
                    this.turn(_getDiffAngle);
                    if (this.getShipSpeed() > 0.0f) {
                        this.accelerateOrBrake(-1.0f);
                    }
                }
            }
            else {
                this.turn(_getDiffAngle4);
                this.accelerateOrBrake(1.0f);
            }
        }
        if (!this.isCharging) {
            if (_getdist <= this.getShipSpeed() / 3.0f && this.abs(_getDiffAngle3) <= 16.0f) {
                if (this.abs(_getDiffAngle2) < 13.0f) {
                    this.accelerateOrBrake(1.0f);
                }
                else {
                    this.accelerateOrBrake(-1.0f);
                }
                this.turn(_getDiffAngle2);
                return;
            }
            if (this.getShipSpeed() >= 4.0f && this.abs(_getDiffAngle3) >= 13.0f) {
                this.turn(_getDiffAngle3 / 3.0f);
            }
            else {
                this.turn(_getDiffAngle);
            }
            this.accelerateOrBrake(1.0f);
            if (this.abs(_getDiffAngle3) <= 9.0f && this.abs(_getDiffAngle) <= 9.0f && (_getdist >= 10.0f || (this.abs(_getDiffAngle2) <= 18.0f && _getdist2 >= 10.0f)) && this.getShipBatteryLevel() >= 13.0f && this.getShipBoostLevel() >= 100.0f) {
                this.useBoost();
            }
        }
    }
}
