package ch.fhnw.lems.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

// done by HiS
@Entity
public class TransportCost {
	@Id
	@Column(unique = true, nullable = false)
	private Long transportcostId;
	@Column(unique = true, nullable = false)
	private Long distance;
	private double pallet1;
	private double pallet2;
	private double pallet3;
	private double pallet4;
	private double pallet5;
	private double pallet6;
	private double pallet7;
	private double pallet8;
	private double pallet9;
	private double pallet10;
	private double pallet11;
	private double pallet12;

	public Long getTransportcostId() {
		return transportcostId;
	}

	public void setTransportcostId(Long transportcostId) {
		this.transportcostId = transportcostId;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public double getPallet1() {
		return pallet1;
	}

	public void setPallet1(double pallet1) {
		this.pallet1 = pallet1;
	}

	public double getPallet2() {
		return pallet2;
	}

	public void setPallet2(double pallet2) {
		this.pallet2 = pallet2;
	}

	public double getPallet3() {
		return pallet3;
	}

	public void setPallet3(double pallet3) {
		this.pallet3 = pallet3;
	}

	public double getPallet4() {
		return pallet4;
	}

	public void setPallet4(double pallet4) {
		this.pallet4 = pallet4;
	}

	public double getPallet5() {
		return pallet5;
	}

	public void setPallet5(double pallet5) {
		this.pallet5 = pallet5;
	}

	public double getPallet6() {
		return pallet6;
	}

	public void setPallet6(double pallet6) {
		this.pallet6 = pallet6;
	}

	public double getPallet7() {
		return pallet7;
	}

	public void setPallet7(double pallet7) {
		this.pallet7 = pallet7;
	}

	public double getPallet8() {
		return pallet8;
	}

	public void setPallet8(double pallet8) {
		this.pallet8 = pallet8;
	}

	public double getPallet9() {
		return pallet9;
	}

	public void setPallet9(double pallet9) {
		this.pallet9 = pallet9;
	}

	public double getPallet10() {
		return pallet10;
	}

	public void setPallet10(double pallet10) {
		this.pallet10 = pallet10;
	}

	public double getPallet11() {
		return pallet11;
	}

	public void setPallet11(double pallet11) {
		this.pallet11 = pallet11;
	}

	public double getPallet12() {
		return pallet12;
	}

	public void setPallet12(double pallet12) {
		this.pallet12 = pallet12;
	}

}
