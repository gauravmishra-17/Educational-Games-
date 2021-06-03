import React, {Component} from 'react'
import axios from 'axios'
import "./contact.css"


class Contact extends Component {
    constructor() {
        super()
        this.handleSubmit = this.handleSubmit.bind(this)
        this.firstNameChanged = this.firstNameChanged.bind(this)
        this.lastNameChanged = this.lastNameChanged.bind(this)
        this.emailChanged = this.emailChanged.bind(this)
        this.messageChanged = this.messageChanged.bind(this)
        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            message: '',
            sent: false
        }
    }
    firstNameChanged(e) {
        this.setState({firstname: e.target.value})
    }
    lastNameChanged(e) {
        this.setState({lastname: e.target.value})
    }
    messageChanged(e) {
        this.setState({message: e.target.value})
    }
    emailChanged(e) {
        this.setState({email: e.target.value})
    }
    async handleSubmit(e) {
        e.preventDefault()
        try {
            const res = await axios('/contacts', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({email: this.state.email, name: this.state.name, message: this.state.message})
            })
            res.status === 200
                ? this.setState({sent: true})
                : this.setState({sent: false})
        } catch (err) {
            console.log(err)
        }
    }
    render() {
        return (<div className={"container"}>
            <div className={"header"}>
                <h1 className={"title"}>Sent us a message! </h1>
            </div>
            <div className={"thanks"}>
            <p className={this.state.sent
                ? ''
                : 'hide-text'}>Thank you for your message.</p></div>
            <form onSubmit={this.handleSubmit} id='form'>
                <div>
                    <p>
                        <label className={"field"} htmlFor="name">Name:</label><br/>
                        <input name='name' type='text' value={this.state.firstName} onChange={this.firstNameChanged}/>
                    </p>
                    <p>
                        <label className={"field"} htmlFor="name">Name:</label><br/>
                        <input name='name' type='text' value={this.state.lastName} onChange={this.lastNameChanged}/>
                    </p>
                    <p>
                        <label className={"field"} htmlFor="email">Email:</label><br/>
                        <input name='email' type='text' value={this.state.email} onChange={this.emailChanged}/>
                    </p>
                    <p>
                        <label className={"field"} htmlFor="message">Message:</label><br/>
                        <textarea name='message' cols={'140'} rows={'10'} type="text" value={this.state.message} onChange={this.messageChanged}/></p>
                    <p><input className={"btn btn-danger submit"} type='submit' value='Send'/>
                    </p>
                </div>
            </form>
        </div>)
    }
}

export default Contact
