from flask.ext.security import RoleMixin, UserMixin
from flask.ext.security import SQLAlchemyUserDatastore, Security

__author__ = 'mingles'

from SmartLockServer import db, app

# Define models
role_user = db.Table('role_user',
                       db.Column('user_id', db.Integer(), db.ForeignKey('user.id')),
                       db.Column('role_id', db.Integer(), db.ForeignKey('role.id')))

user_lock = db.Table('user_lock',
                       db.Column('user_id', db.Integer(), db.ForeignKey('user.id')),
                       db.Column('lock_id', db.Integer(), db.ForeignKey('lock.id')),
                       db.Column('is_owner', db.Boolean()))


class Role(db.Model, RoleMixin):
    id = db.Column(db.Integer(), primary_key=True)
    name = db.Column(db.String(80), unique=True)
    description = db.Column(db.String(255))


class User(db.Model, UserMixin):
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(255), unique=True)
    first_name = db.Column(db.String(255), unique=True)
    last_name = db.Column(db.String(255), unique=True)
    password = db.Column(db.String(255),)
    active = db.Column(db.Boolean())
    confirmed_at = db.Column(db.DateTime())
    roles = db.relationship('Role', secondary=role_user,
                            backref=db.backref('users', lazy='dynamic'))


class Lock(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    owner = db.Column(db.String(255), unique=True)
    locked = db.Column(db.Boolean())


# Setup Flask-Security
user_datastore = SQLAlchemyUserDatastore(db, User, Role)
security = Security(app, user_datastore)
