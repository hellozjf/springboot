package com.hellozjf.test.springboot.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Jingfeng Zhou
 */
@Data
@Entity
@Table(name = "T_XT_YH")
public class T_XT_YH {

    @Id
    @Column(name="YH_ID")
    private String yhId;

    @Column(name="YH_DM")
    private String yhDm;

    @Column(name="YH_MC")
    private String yhMc;

    @Column(name="MM")
    private String mm;

    @Column(name="XB")
    private Character xb;

    @Column(name="YH_LX")
    private Character yhLx;

    @Column(name="YH_JS")
    private String yhJs;

    @Column(name="XH")
    private Double xh;

    @Column(name="YX_BJ")
    private Character yxBj;

    @Column(name="SC_BJ")
    private Character scBj;

    @Column(name="LR_RQ")
    private String lrRq;

    @Column(name="XG_RQ")
    private String xgRq;

    @Column(name="CZRY_ID")
    private String czryId;

    @Column(name="EMAIL")
    private String email;

    @Column(name="BZ")
    private String bz;

    @Column(name="USERXZQH")
    private String userxzqh;

    @Column(name="USERISSIGN")
    private String userissign;

    @Column(name="USERMOBILE")
    private String usermobile;

    @Column(name="USERTYPE")
    private String usertype;

    @Column(name="USERLOGINCODE")
    private String userlogincode;

    @Column(name="USERPHONE")
    private String userphone;

    @Column(name="USERJGBH")
    private String userjgbh;

    @Column(name="GXSJ")
    private String gxsj;

    @Column(name="BBH")
    private String bbh;

    @Column(name="JHZT")
    private String jhzt;

    @Column(name="ZXGH")
    private String zxgh;

    @Column(name="IMGSRC")
    private String imgsrc;

    @Lob
    @Column(name="IMGBLOB")
    private byte[] imgblob;

    @Column(name="BMTXLBH")
    private String bmtxlbh;

    @Column(name="SFZZ")
    private Character sfzz;

    @Column(name="RYLX")
    private Character rylx;

    @Column(name="USER_STATE")
    private String userState;

    @Column(name="LAST_MODIFY")
    private Date lastModify;

    @Column(name="RESERVE")
    private String reserve;
}
